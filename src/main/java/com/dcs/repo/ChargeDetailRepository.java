package com.dcs.repo;

import com.dcs.entity.ChargeDetailRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChargeDetailRepository extends JpaRepository<ChargeDetailRecord, Long> {
    Page<ChargeDetailRecord> findByVehicleId(String vehicleId, Pageable pageable);

    //check if new period overlaps existing one --------
    //-------------------/existing period/--------------
    //--------------/new period/------------------------
    //----------------------------/new period/----------
    @Query(value = "SELECT cdr FROM ChargeDetailRecord cdr WHERE " +
            "((cdr.startTime >= :startTime AND cdr.endTime <= :endTime) OR " +
            "(cdr.startTime >= :startTime AND cdr.endTime <= :endTime)) AND " +
            "cdr.vehicleId = :vehicleId")
    List<ChargeDetailRecord> findByOverlappingRecordsWithinDateRangeAndVehicleId(
            LocalDateTime startTime,
            LocalDateTime endTime,
            String vehicleId
    );
}
