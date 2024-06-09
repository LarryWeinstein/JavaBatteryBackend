package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/battery")
public class BatteryController {

    private final BatteryService batteryService;

    @Autowired
    public BatteryController(BatteryService batteryService) {
        this.batteryService = batteryService;
    }

    @GetMapping("/listall/")
    public List<Battery> listAll() {
        return batteryService.getAllBatteries();
    }

    //find one battery by id
    @GetMapping("/{id}")
    public Battery getById(@PathVariable Long id) {
        return batteryService.findBatteryById(id);
    }

    @PostMapping("/")
    public Battery createBattery(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        return batteryService.createBattery(name);
    }


    //delete battery
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        batteryService.deleteBatteryById(id);
    }

    //update battery name

    //update battery last updated
    @PatchMapping("/{id}/update-data")
    public Battery upDate(@PathVariable Long id){
        return batteryService.updateDateUpdated(id);
    }
}
