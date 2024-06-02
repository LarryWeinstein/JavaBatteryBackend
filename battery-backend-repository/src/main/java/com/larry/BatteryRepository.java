package com.larry;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<Battery, Long> {
    Battery findFirstByName(String name);
}
