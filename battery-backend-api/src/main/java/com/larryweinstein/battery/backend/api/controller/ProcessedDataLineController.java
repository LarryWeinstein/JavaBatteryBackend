package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.service.BatteryService;
import com.larryweinstein.battery.backend.service.ProcessedDataLineService;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessedDataLineController {
    private final BatteryService batteryService;
    private final ProcessedDataLineService processedDataLineService;

    @Autowired
    public ProcessedDataLineController(BatteryService batteryService, ProcessedDataLineService processedDataLineService){
        this.batteryService = batteryService;
        this.processedDataLineService = processedDataLineService;
    }

}
