package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.model.Battery;
import com.larryweinstein.battery.backend.repository.BatteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BatteryService {
    private final BatteryRepository batteryRepository;

    @Autowired
    public BatteryService(BatteryRepository batteryRepository) {
        this.batteryRepository = batteryRepository;
    }


    public List<Battery> getAllBatteries() {
        List<Battery> batteries = batteryRepository.findAll();
        return batteries;
    }

    //change createBattery to create since everything is regarding battery
    public Battery createBattery(String name) {
        Battery battery = new Battery();
        battery.setName(name);
        return batteryRepository.saveAndFlush(battery);
    }

    public Battery findBatteryById(Long id) {
        return batteryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Could not find battery with id" + id));
    }


    public void deleteBatteryById(Long id) {
        batteryRepository.deleteById(id);
    }


    public Battery updateDateUpdated(Long id, LocalDate dateUpdated) {
        Battery found = batteryRepository.getById(id);
        LocalDate now = LocalDate.now();
        found.setLastUpdated(now);
        return batteryRepository.saveAndFlush(found);
    }

}
