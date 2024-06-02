package com.larry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Long> {
}
