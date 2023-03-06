package com.dcs.repo;

import com.dcs.entity.ChargeDetailRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeDetailRepository extends JpaRepository<ChargeDetailRecord, Long> {
    Page<ChargeDetailRecord> findByVehicleId(String vehicleId, Pageable pageable);
}
