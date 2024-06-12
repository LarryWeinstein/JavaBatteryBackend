package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        return batteryService.getAll();
    }

    @GetMapping("/{id}")
    public Battery getById(@PathVariable Long id) {
        return batteryService.findById(id);
    }

    @PostMapping("/")
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

    @PostMapping("/uploadcsv")
    public void uploadCSV(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Print each line of the CSV file
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
