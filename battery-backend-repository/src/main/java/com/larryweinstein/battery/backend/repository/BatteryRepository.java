package com.larryweinstein.battery.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.larryweinstein.battery.backend.model.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
    Battery findFirstByName(String name);
}
