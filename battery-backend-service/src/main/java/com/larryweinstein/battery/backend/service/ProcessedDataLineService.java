package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.repository.ProcessedDataLineRepository;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProcessedDataLine> getAll() {
        List<ProcessedDataLine> lines = processedDataLineRepository.findAll();
        return lines;
    }

    public List<ProcessedDataLine> getByBatteryId(Long id){
        List<ProcessedDataLine> lines = processedDataLineRepository.findByBatteryId(id);
        return lines;
    }

    public void deleteByBatteryId(Long id){
        processedDataLineRepository.deleteByBatteryId(id);
    }

}
