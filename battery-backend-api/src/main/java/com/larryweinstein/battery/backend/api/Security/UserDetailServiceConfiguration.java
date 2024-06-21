package com.larryweinstein.battery.backend.api.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfiguration {
    @Bean
    UserDetailsService userDetailsService(){
        return new InMemoryUserDetailsManager(User.withDefaultPasswordEncoder()
                .username("trogdor").password("burninator").roles("USER").build());
    }
}
