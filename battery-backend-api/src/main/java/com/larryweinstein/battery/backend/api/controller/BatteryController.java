package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.service.BatteryService;
import com.larryweinstein.battery.backend.service.ProcessedDataLineService;
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
    private final ProcessedDataLineService processedDataLineService;

    @Autowired
    public BatteryController(BatteryService batteryService, ProcessedDataLineService processedDataLineService) {
        this.batteryService = batteryService;
        this.processedDataLineService = processedDataLineService;
    }

    //Can send to root mapping
    //Can have multiple queries with same root if different types
    @GetMapping("/")
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


    //use CSV library and move business logic to service
    //CommonCSV
    //Get stream from multpart file
    @PostMapping("/upload_csv")
    public void uploadCSV(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Battery battery = batteryService.create(fileName);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            //get first line
            String line = br.readLine();
            //setup variables for loop
            double lastDischargeCap = 0.0d;
            double lastChargeCap = 0.0d;
            double chargeCap = 0.0d;
            double dischargeCap = 0.0d;
            int lastCycle = 1;
            int cycleNo = 1;
            while ((line = br.readLine()) != null) {
                // Print each line of the CSV file
                String[] vals = line.split(",");
                cycleNo = Integer.valueOf(vals[5]);
                chargeCap = Double.valueOf(vals[8]);
                dischargeCap = Double.valueOf(vals[9]);
                if (cycleNo > lastCycle) {
                    //get values to input, cycleNo is last cycle
                    double chargeCapForDataLine = chargeCap - lastChargeCap;
                    double dischargeCapForDataLine = dischargeCap - lastDischargeCap;
                    ProcessedDataLine pdl = processedDataLineService.createProcessedData(battery, lastCycle,
                            chargeCapForDataLine, dischargeCapForDataLine);
                    lastCycle = cycleNo;
                    lastDischargeCap = dischargeCap;
                    lastChargeCap = chargeCap;
                }
            }
            //process last cycle
            double chargeCapForDataLine = chargeCap - lastChargeCap;
            double dischargeCapForDataLine = dischargeCap - lastDischargeCap;
            ProcessedDataLine pdl = processedDataLineService.createProcessedData(battery, cycleNo,
                    chargeCapForDataLine, dischargeCapForDataLine);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
