package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.service.BatteryService;
import com.larryweinstein.battery.backend.service.ProcessedDataLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping
    public List<Battery> listAll() {
        return batteryService.getAll();
    }

    @GetMapping("/{id}")
    public Battery getById(@PathVariable Long id) {
        return batteryService.findById(id);
    }

    @PostMapping
    public Battery createBattery(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        return batteryService.create(name);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        batteryService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Battery updateName(@PathVariable Long id, @RequestBody Map<String, String> updateData) {
        String name = updateData.get("name");
        return batteryService.changeName(id, name);
    }

    @PatchMapping("/{id}/update-date")
    public Battery upDate(@PathVariable Long id) {
        return batteryService.updateDateUpdated(id);
    }

    @PostMapping("/upload-csv")
    public void uploadCSV(@RequestParam("file") MultipartFile file) {
        batteryService.processCSV(file);
    }

    //To get Tablesaw working
    @PostMapping("/upload-data")
    public void uploadData(@RequestParam("file") MultipartFile file) {
        batteryService.processData(file);
    }
}
