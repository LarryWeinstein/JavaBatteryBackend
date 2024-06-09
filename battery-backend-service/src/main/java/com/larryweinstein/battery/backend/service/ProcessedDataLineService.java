package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.repository.ProcessedDataLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessedDataLineService {
    private final ProcessedDataLineRepository processedDataLineRepository;

    @Autowired
    public ProcessedDataLineService(ProcessedDataLineRepository processedDataLineRepository) {
        this.processedDataLineRepository = processedDataLineRepository;
    }
    //use JPQL to create custom query
    //use Spring Data API to create declarative method to get all data by battery_id
    //find all by battery id

    public ProcessedDataLine createProcessedData(Battery battery, int cycleNumber,
                                                 double chargeCapacity, double dischargeCapacity) {
        ProcessedDataLine processedDataLine = new ProcessedDataLine();
        processedDataLine.setBattery(battery);
        processedDataLine.setCycleNumber(cycleNumber);
        processedDataLine.setChargeCapacity(chargeCapacity);
        processedDataLine.setDischargeCapacity(dischargeCapacity);
        return processedDataLineRepository.saveAndFlush(processedDataLine);
    }
}
