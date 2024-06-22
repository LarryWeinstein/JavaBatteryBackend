package com.larryweinstein.battery.backend.repository;

import com.larryweinstein.battery.backend.model.ERole;
import com.larryweinstein.battery.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
