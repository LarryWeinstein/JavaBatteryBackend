package com.larryweinstein.battery.backend.service;

import com.larryweinstein.battery.backend.api.dtos.LoginUserDto;
import com.larryweinstein.battery.backend.api.dtos.RegisterUserDto;
import com.larryweinstein.battery.backend.model.User;
import com.larryweinstein.battery.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDto input) {
        var userName = input.getUsername();
        var email = input.getEmail();
        var password = input.getPassword();
        var encodedPassword = passwordEncoder.encode(password);
        User user = new User(userName, email, encodedPassword);
        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByusername(input.getUsername())
                .orElseThrow();
    }
}