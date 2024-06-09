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

}
