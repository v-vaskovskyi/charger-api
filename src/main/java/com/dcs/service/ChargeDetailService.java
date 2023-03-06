package com.dcs.service;

import com.dcs.entity.ChargeDetailRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ChargeDetailService {
    ChargeDetailRecord addNewChargeDetailRecord(ChargeDetailRecord chargeDetailRecord);

    ChargeDetailRecord getChargeDetailRecordById(Long chargeDetailRecordId);

    Page<ChargeDetailRecord> findChargeDetailRecordsByVehicleId(String vehicleId, Pageable pageable);
}
