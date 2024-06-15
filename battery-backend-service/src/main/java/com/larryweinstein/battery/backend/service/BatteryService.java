package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedDataLine;
import com.larryweinstein.battery.backend.repository.BatteryRepository;
import com.larryweinstein.battery.backend.repository.ProcessedDataLineRepository;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.tablesaw.aggregate.AggregateFunction;
import tech.tablesaw.aggregate.AggregateFunctions;
import tech.tablesaw.api.Table;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Service
public class BatteryService {
    private final BatteryRepository batteryRepository;
    private final ProcessedDataLineRepository processedDataLineRepository;

    @Autowired
    public BatteryService(BatteryRepository batteryRepository, ProcessedDataLineRepository processedDataLineRepository) {
        this.batteryRepository = batteryRepository;
        this.processedDataLineRepository = processedDataLineRepository;
    }


    public List<Battery> getAll() {
        List<Battery> batteries = batteryRepository.findAll();
        return batteries;
    }

    //change createBattery to create since everything is regarding battery
    public Battery create(String name) {
        Battery battery = new Battery();
        battery.setName(name);
        return batteryRepository.saveAndFlush(battery);
    }

    public Battery findById(Long id) {
        return batteryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find battery with id" + id));
    }


    public void deleteById(Long id) {
        batteryRepository.deleteById(id);
    }


    public Battery updateDateUpdated(Long id) {
        Battery found = batteryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find battery with id" + id));
        LocalDate now = LocalDate.now();
        found.setLastUpdated(now);
        return batteryRepository.saveAndFlush(found);
    }

    public Battery changeName(Long id, String name){
        Battery found = batteryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find battery with id" + id));
        found.setName(name);
        return batteryRepository.saveAndFlush(found);
    }

    public ProcessedDataLine createProcessedData(Battery battery, int cycleNumber,
                                                 double chargeCapacity, double dischargeCapacity) {
        ProcessedDataLine processedDataLine = new ProcessedDataLine();
        processedDataLine.setBattery(battery);
        processedDataLine.setCycleNumber(cycleNumber);
        processedDataLine.setChargeCapacity(chargeCapacity);
        processedDataLine.setDischargeCapacity(dischargeCapacity);
        return processedDataLineRepository.saveAndFlush(processedDataLine);
    }

    public void processCSV(MultipartFile file){
        String fileName = file.getOriginalFilename();
        Battery battery = create(fileName);
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
                    ProcessedDataLine pdl = createProcessedData(battery, lastCycle,
                            chargeCapForDataLine, dischargeCapForDataLine);
                    lastCycle = cycleNo;
                    lastDischargeCap = dischargeCap;
                    lastChargeCap = chargeCap;
                    System.out.println(cycleNo);
                }
            }
            //process last cycle
            double chargeCapForDataLine = chargeCap - lastChargeCap;
            double dischargeCapForDataLine = dischargeCap - lastDischargeCap;
            ProcessedDataLine pdl = createProcessedData(battery, cycleNo,
                    chargeCapForDataLine, dischargeCapForDataLine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processData(MultipartFile file){
        String fileName = file.getOriginalFilename();
        try(InputStream inputStream = file.getInputStream()) {
            Table table = Table.read().csv(inputStream, fileName);
            Table chargeCapacities = table.summarize("Charge_Capacity(Ah)", AggregateFunctions.range)
                    .by("Cycle_Index");
            System.out.println(chargeCapacities);
            Table dischargeCapacities = table.summarize("Discharge_Capacity(Ah)", AggregateFunctions.range)
                    .by("Cycle_Index");
            System.out.println(dischargeCapacities);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
