package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.ProcessedData;
import org.springframework.data.jpa.repository.JpaRepository;

//enable configuration entity, repository configurations
public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Long> {
}
