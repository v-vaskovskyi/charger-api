package com.dcs.controller;

import com.dcs.dto.ChargeDetailRecordDto;
import com.dcs.entity.ChargeDetailRecord;
import com.dcs.service.ChargeDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/cdr")
public class ChargeDetailController {

    private final ChargeDetailService chargeDetailService;
    private final ModelMapper modelMapper;

    @PostMapping
    ChargeDetailRecordDto registerNewChargeDetailRecord(
            @RequestBody @Valid ChargeDetailRecordDto chargeDetailRecordDto) {
        return convertToDto(chargeDetailService.addNewChargeDetailRecord(
                        convertToEntity(chargeDetailRecordDto)));
    }

    @GetMapping
    @RequestMapping("/id/{chargeDetailRecordId}")
    ChargeDetailRecordDto findChargeDetailRecordById(
            @PathVariable("chargeDetailRecordId") Long chargeDetailRecordId) {
        return convertToDto(chargeDetailService.getChargeDetailRecordById(chargeDetailRecordId));
    }

    @GetMapping
    @RequestMapping("/vehicleId/{vehicleId}")
    Page<ChargeDetailRecordDto> findByChargeDetailRecordsByVehicleId(
            @PathVariable("vehicleId") String vehicleId, Pageable pageable) {
        return chargeDetailService.findChargeDetailRecordsByVehicleId(vehicleId, pageable)
                .map(this::convertToDto);
    }

    private ChargeDetailRecordDto convertToDto(ChargeDetailRecord chargeDetailRecord) {
        return modelMapper.map(chargeDetailRecord, ChargeDetailRecordDto.class);
    }

    private ChargeDetailRecord convertToEntity(ChargeDetailRecordDto chargeDetailRecordDto) {
        return modelMapper.map(chargeDetailRecordDto, ChargeDetailRecord.class);
    }
}
