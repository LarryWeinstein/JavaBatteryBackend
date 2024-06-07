package com.larryweinstein.battery.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessedDataService {
    private final ProcessedDataRepository processedDataRepository;
    @Autowired
    public ProcessedDataService(ProcessedDataRepository processedDataRepository){
        this.processedDataRepository = processedDataRepository;
    }
    //use JPQL to create custom query
    //use Spring Data API to create declarative method to get all data by battery_id
    //find all by battery id

    public ProcessedData createProcessedData(Battery battery, int cycleNumber,
                                             double chargeCapacity, double dischargeCapacity){
        ProcessedData processedData = new ProcessedData(battery, cycleNumber, chargeCapacity, dischargeCapacity);
        return processedDataRepository.saveAndFlush(processedData);
    }
}
