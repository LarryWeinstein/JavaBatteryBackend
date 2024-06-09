package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import org.springframework.data.jpa.repository.JpaRepository;

//enable configuration entity, repository configurations
public interface ProcessedDataLineRepository extends JpaRepository<ProcessedDataLine, Long> {
}
