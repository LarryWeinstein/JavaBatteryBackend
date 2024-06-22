package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByusername(String username);
    Boolean existsByusername(String username);
    Boolean existsByEmail(String email);
}
