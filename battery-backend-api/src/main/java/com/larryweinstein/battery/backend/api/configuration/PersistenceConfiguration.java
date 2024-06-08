package com.larryweinstein.battery.backend.api.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.larryweinstein.battery.backend.model")
@EnableJpaRepositories(basePackages = "com.larryweinstein.battery.backend.repository")
public class PersistenceConfiguration {

}
