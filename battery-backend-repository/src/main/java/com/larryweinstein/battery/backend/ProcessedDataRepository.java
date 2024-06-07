package com.larryweinstein.battery.backend;

import org.springframework.data.jpa.repository.JpaRepository;

//enable configuration entity, repository configurations
public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Long> {
}
