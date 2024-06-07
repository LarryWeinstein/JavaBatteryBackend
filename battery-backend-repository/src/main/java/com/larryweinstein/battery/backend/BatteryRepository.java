package com.larryweinstein.battery.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import com.larryweinstein.battery.backend.Battery;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
    Battery findFirstByName(String name);
}
