package com.larryweinstein.battery.backend.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.larryweinstein.battery.backend"})
public class BatteryBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatteryBackendApplication.class, args);
    }
}
