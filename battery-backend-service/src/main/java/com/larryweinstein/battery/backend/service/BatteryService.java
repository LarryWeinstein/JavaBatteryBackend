package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.model.ProcessedData;
import com.larryweinstein.battery.backend.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BatteryService {
    private final BatteryRepository batteryRepository;

    @Autowired
    public BatteryService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }

    //use id to perform search
    //to provide basic info create dto to provide limited info
    public List<String> getBatteryNames() {
        List<Battery> batteries = batteryRepository.findAll();
        List<String> names = batteries.stream().map(Battery::getName).collect(Collectors.toList());
        return names;
    }

    //change createBattery to create since everything is regarding battery
    public Battery createBattery(String name) {
        Battery battery = new Battery(name);
        return batteryRepository.saveAndFlush(battery);
    }

    public Battery findBatteryById(Long id) {
        return batteryRepository.getById(id);
    }

    public Battery findBatteryByName(String name) {
        return batteryRepository.findFirstByName(name);
    }

    public void deleteBatteryById(Long id) {
        batteryRepository.deleteById(id);
    }

    public void deleteBatteryByName(String name) {
        Battery found = batteryRepository.findFirstByName(name);
        Long id = found.getId();
        batteryRepository.deleteById(id);
    }

    public List<ProcessedData> getBatteryCycleData(Long id) {
        Battery found = batteryRepository.getById(id);
        return found.getProcessedData();
    }

    public Battery updateDateUpdated(Long id, LocalDate dateUpdated) {
        Battery found = batteryRepository.getById(id);
        found.setLastUpdated(dateUpdated);
        return batteryRepository.saveAndFlush(found);
    }


}
