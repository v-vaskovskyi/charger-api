package com.dcs;

import com.dcs.dto.ChargeDetailRecordDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ChargerApiApplication.class})
public class ChargeDetailRecordValidationUnitTest {

	private final String VEHICLE_NO = "AF5595";
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	private ObjectMapper objectMapper;

	@Value("${message.startDate.before.lastDate}")
	private String messageStartDateOverLastDate;

	@Value("${message.vehicleId.min.max}")
	private String messageVehicleIdMinMaxValues;

	@Value("${message.startTime.future.or.present}")
	private String messageStartTimeFutureOrPresent;

	@Value("${message.cost.positive}")
	private String messageCostMustBePositive;

	@BeforeEach
	void init() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(webApplicationContext)
				.build();
		objectMapper = JsonMapper.builder()
				.findAndAddModules()
				.build();
	}

	@Test
	@DisplayName("POST /api/v1/cdr Start Date In Past Failure")
	public void testChargeDetailRecordStartDateInPastFails() throws Exception {
		ChargeDetailRecordDto chargeDetailRecordDto = initChargeDetailRecord();
		chargeDetailRecordDto.setStartTime(LocalDateTime.now().minusHours(5));
		performCreateRecordPostAndValidateException(
				chargeDetailRecordDto,
				this.messageStartTimeFutureOrPresent);
	}

	@Test
	@DisplayName("POST /api/v1/cdr Cost is zero Failure")
	public void testChargeDetailRecordCostNotPositive() throws Exception {
		ChargeDetailRecordDto chargeDetailRecordDto = initChargeDetailRecord();
		chargeDetailRecordDto.setCost(0.0);
		performCreateRecordPostAndValidateException(
				chargeDetailRecordDto,
				this.messageCostMustBePositive);
	}

	@Test
	@DisplayName("POST /api/v1/cdr Start Date Ahead End Date Failure")
	public void testChargeDetailRecordStartDateAheadEndDateFails() throws Exception {
		ChargeDetailRecordDto chargeDetailRecordDto = initChargeDetailRecord();
		chargeDetailRecordDto.setStartTime(LocalDateTime.now().plusHours(5));
		chargeDetailRecordDto.setEndTime(LocalDateTime.now().plusHours(1));
		performCreateRecordPostAndValidateException(
				chargeDetailRecordDto,
				this.messageStartDateOverLastDate);
	}

	@Test
	@DisplayName("POST /api/v1/cdr Vehicle Id incorrect length Failure")
	public void testChargeDetailRecordVehicleIdIncorrectLengthFails() throws Exception {
		ChargeDetailRecordDto chargeDetailRecordDto = initChargeDetailRecord();
		chargeDetailRecordDto.setVehicleId("123456789101112131415");
		performCreateRecordPostAndValidateException(
				chargeDetailRecordDto,
				this.messageVehicleIdMinMaxValues);
	}

	@Test
	@DisplayName("POST /api/v1/cdr Positive")
	public void testChargeDetailRecordPositive() throws Exception {
		String jsonBody = objectMapper.writeValueAsString(initChargeDetailRecord());
		mockMvc.perform(post("/api/v1/cdr")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBody))
				.andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString().contains(VEHICLE_NO);
	}

	//create Charge Detail Record and validate the output depending on it's values
	private void performCreateRecordPostAndValidateException(
			ChargeDetailRecordDto chargeDetailRecordDto,
			String expectedMessage
	) throws Exception {
		String jsonBody = objectMapper.writeValueAsString(chargeDetailRecordDto);
		Exception exception = mockMvc.perform(post("/api/v1/cdr")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonBody))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
				.andReturn().getResolvedException();
		assertEquals(exception.getClass(), MethodArgumentNotValidException.class);
		assertTrue(exception.getMessage() != null && exception.getMessage().contains(expectedMessage));
	}

	//init normal record
	private ChargeDetailRecordDto initChargeDetailRecord() {
		ChargeDetailRecordDto chargeDetailRecordDto = new ChargeDetailRecordDto();
		chargeDetailRecordDto.setVehicleId(VEHICLE_NO);
		chargeDetailRecordDto.setStartTime(LocalDateTime.now().plusMinutes(30));
		chargeDetailRecordDto.setEndTime(LocalDateTime.now().plusMinutes(90));
		chargeDetailRecordDto.setCost(5.5);
		return chargeDetailRecordDto;
	}
}
