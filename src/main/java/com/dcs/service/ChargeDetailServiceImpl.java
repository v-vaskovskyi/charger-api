package com.dcs.service;

import com.dcs.entity.ChargeDetailRecord;
import com.dcs.exception.ChangeDetailRecordExistsWithinDateRangeForVehicle;
import com.dcs.exception.ChargeDetailRecordByIdNotFoundException;
import com.dcs.repo.ChargeDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargeDetailServiceImpl implements ChargeDetailService {

    private final ChargeDetailRepository chargeDetailRepository;

    @Value("${message.data.range.overlaps}")
    private String messageDateRangeOverlaps;

    @Value("${message.record.not.exists}")
    private String messageRecordNotExits;

    @Override
    public ChargeDetailRecord addNewChargeDetailRecord(ChargeDetailRecord chargeDetailRecord) {
        //check if there is already records overlapping date range for the current vehicle
        List<ChargeDetailRecord> existingRecords = chargeDetailRepository.
                findByOverlappingRecordsWithinDateRangeAndVehicleId(
                        chargeDetailRecord.getStartTime(),
                        chargeDetailRecord.getEndTime(),
                        chargeDetailRecord.getVehicleId()
                );
        if (!CollectionUtils.isEmpty(existingRecords)) {
            throw new ChangeDetailRecordExistsWithinDateRangeForVehicle(
                    String.format(this.messageDateRangeOverlaps,
                            chargeDetailRecord.getStartTime(),
                            chargeDetailRecord.getEndTime(),
                            chargeDetailRecord.getVehicleId())
            );
        }
        return chargeDetailRepository.save(chargeDetailRecord);
    }

    @Override
    public ChargeDetailRecord getChargeDetailRecordById(Long chargeDetailRecordId) {
        return chargeDetailRepository.findById(chargeDetailRecordId)
                .orElseThrow(() -> new ChargeDetailRecordByIdNotFoundException(
                        String.format(this.messageRecordNotExits, chargeDetailRecordId)
                ));
    }

    @Override
    public Page<ChargeDetailRecord> findChargeDetailRecordsByVehicleId(String vehicleId, Pageable pageable) {
        return chargeDetailRepository.findByVehicleId(vehicleId, pageable);
    }
}
