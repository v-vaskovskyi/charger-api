package com.dcs.service;

import com.dcs.entity.ChargeDetailRecord;
import com.dcs.exception.ChargeDetailRecordByIdNotFoundException;
import com.dcs.repo.ChargeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeDetailServiceImpl implements ChargeDetailService {

    private final ChargeDetailRepository chargeDetailRepository;
    @Override
    public ChargeDetailRecord addNewChargeDetailRecord(ChargeDetailRecord chargeDetailRecord) {
        return chargeDetailRepository.save(chargeDetailRecord);
    }

    @Override
    public ChargeDetailRecord getChargeDetailRecordById(Long chargeDetailRecordId) {
        return chargeDetailRepository.findById(chargeDetailRecordId)
                .orElseThrow(() -> new ChargeDetailRecordByIdNotFoundException(
                        String.format("Charge Detail Record with the id %d does not exist.", chargeDetailRecordId)
                ));
    }

    @Override
    public Page<ChargeDetailRecord> findChargeDetailRecordsByVehicleId(String vehicleId, Pageable pageable) {
        return chargeDetailRepository.findByVehicleId(vehicleId, pageable);
    }
}
