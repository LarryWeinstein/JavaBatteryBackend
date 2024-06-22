package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByusername(String username);
    Boolean existsByusername(String username);
    Boolean existsByEmail(String email);
}
