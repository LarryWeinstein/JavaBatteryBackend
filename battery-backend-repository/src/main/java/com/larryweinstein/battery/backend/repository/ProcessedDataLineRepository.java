package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//enable configuration entity, repository configurations
public interface ProcessedDataLineRepository extends JpaRepository<ProcessedDataLine, Long> {
    @Query(value = "Select pl FROM ProcessedDataLine pl WHERE pl.battery.id = :batteryId")
    List<ProcessedDataLine> findByBatteryId(@Param("batteryId") Long battery_id);

    @Modifying
    @Transactional
    @Query(value = "Delete FROM ProcessedDataLine pl WHERE pl.battery.id = :batteryId")
    void deleteByBatteryId(@Param("batteryId") Long battery_id);
}
