package com.dcs.dto;

import com.dcs.validation.ChargeDetailRecordStartDateBeforeLastDate;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ChargeDetailRecordStartDateBeforeLastDate
public class ChargeDetailRecordDto {
    private Long sessionId;
    @NotBlank
    @Size(min = 5, max = 10, message = "{message.vehicleId.min.max}")
    private String vehicleId;
    @FutureOrPresent(message = "{message.startTime.future.or.present}")
    @NotNull
    private LocalDateTime startTime;
    @NotNull
    private LocalDateTime endTime;
    @Positive(message = "{message.cost.positive}")
    private Double cost;
}
