package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.service.BatteryService;
import com.larryweinstein.battery.backend.service.ProcessedDataLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PostMapping("/")
    public ProcessedDataLine create(@RequestBody Map<String, String> params){
        Long batteryId = Long.valueOf(params.get("battery_id"));
        Battery parentBattery = batteryService.findById(batteryId);
        int cycle = Integer.valueOf(params.get("cycle_no"));
        Double chargeCapacity = Double.valueOf(params.get("charge_capacity"));
        Double dischargeCapacity = Double.valueOf(params.get("discharge_capacity"));
        return processedDataLineService.createProcessedData(parentBattery, cycle, chargeCapacity, dischargeCapacity);
    }

    @GetMapping("/listall/")
    public List<ProcessedDataLine> listall(){
        return processedDataLineService.getAll();
    }


    @GetMapping("/listbyid/{id}")
    public List<ProcessedDataLine> listById(@PathVariable Long id){
        return processedDataLineService.getByBatteryId(id);
    }

    @DeleteMapping("/{id}")
    public void deleteByBatteryId(@PathVariable Long id){
        processedDataLineService.deleteByBatteryId(id);
    }

}
