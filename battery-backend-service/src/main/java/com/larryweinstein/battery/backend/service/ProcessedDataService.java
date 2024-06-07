package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedData;
import com.larryweinstein.battery.backend.repository.ProcessedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessedDataService {
    private final ProcessedDataRepository processedDataRepository;

    @Autowired
    public ProcessedDataService(ProcessedDataRepository processedDataRepository) {
        this.processedDataRepository = processedDataRepository;
    }
    //use JPQL to create custom query
    //use Spring Data API to create declarative method to get all data by battery_id
    //find all by battery id

    public ProcessedData createProcessedData(Battery battery, int cycleNumber,
                                             double chargeCapacity, double dischargeCapacity) {
        ProcessedData processedData = new ProcessedData();
        processedData.setBattery(battery);
        processedData.setCycleNumber(cycleNumber);
        processedData.setChargeCapacity(chargeCapacity);
        processedData.setDischargeCapacity(dischargeCapacity);
        return processedDataRepository.saveAndFlush(processedData);
    }
}
