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
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterUserDto input) {
        var userName = input.getUsername();
        var email = input.getEmail();
        var password = input.getPassword();
        var encodedPassword = passwordEncoder.encode(password);
        //User user = new User(userName, email, encodedPassword);
        User user = new User();
        user.setUsername(userName);
        user.setEmail(email);
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    public User login(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}