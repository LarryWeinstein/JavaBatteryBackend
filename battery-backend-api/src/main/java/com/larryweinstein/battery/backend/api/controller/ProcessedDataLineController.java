package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.service.BatteryService;
import com.larryweinstein.battery.backend.service.ProcessedDataLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/processeddataline")
public class ProcessedDataLineController {
    private final BatteryService batteryService;
    private final ProcessedDataLineService processedDataLineService;

    @Autowired
    public ProcessedDataLineController(BatteryService batteryService, ProcessedDataLineService processedDataLineService){
        this.batteryService = batteryService;
        this.processedDataLineService = processedDataLineService;
    }

    //create processed data line
    @PostMapping("/")
    public ProcessedDataLine create(@RequestBody Map<String, String> params){
        Long batteryId = Long.valueOf(params.get("battery_id"));
        Battery parentBattery = batteryService.findById(batteryId);
        int cycle = Integer.valueOf(params.get("cycle_no"));
        Double chargeCapacity = Double.valueOf(params.get("charge_capacity"));
        Double dischargeCapacity = Double.valueOf(params.get("discharge_capacity"));
        return processedDataLineService.createProcessedData(parentBattery, cycle, chargeCapacity, dischargeCapacity);
    }

    //get info for processed data line
    //can't figure out why it only works with toString
    @GetMapping("/listall/")
    public String listall(){
        return processedDataLineService.getAll().toString();
    }

    //get all processed data lines for a given battery

    //delete all processed data lines for a given battery

}
