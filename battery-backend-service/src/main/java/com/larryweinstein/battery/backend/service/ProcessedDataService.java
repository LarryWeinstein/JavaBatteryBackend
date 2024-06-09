package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.repository.ProcessedDataLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessedDataService {
    private final ProcessedDataLineRepository processedDataRepository;

    @Autowired
    public ProcessedDataService(ProcessedDataLineRepository processedDataRepository) {
        this.processedDataRepository = processedDataRepository;
    }
    //use JPQL to create custom query
    //use Spring Data API to create declarative method to get all data by battery_id
    //find all by battery id

    public ProcessedDataLine createProcessedData(Battery battery, int cycleNumber,
                                                 double chargeCapacity, double dischargeCapacity) {
        ProcessedDataLine processedData = new ProcessedDataLine();
        processedData.setBattery(battery);
        processedData.setCycleNumber(cycleNumber);
        processedData.setChargeCapacity(chargeCapacity);
        processedData.setDischargeCapacity(dischargeCapacity);
        return processedDataRepository.saveAndFlush(processedData);
    }
}
