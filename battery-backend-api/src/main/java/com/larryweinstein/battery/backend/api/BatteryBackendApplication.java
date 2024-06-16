package com.larryweinstein.battery.backend.api;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication(scanBasePackages = {"com.larryweinstein.battery.backend"})
public class BatteryBackendApplication {
    @Bean
    UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(User.withDefaultPasswordEncoder()
                .username("trogdor").password("burninator").roles("USER").build());
    }
    public static void main(String[] args) {
        SpringApplication.run(BatteryBackendApplication.class, args);
    }
}
