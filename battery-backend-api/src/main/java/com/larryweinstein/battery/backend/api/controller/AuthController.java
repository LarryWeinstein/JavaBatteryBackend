package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.api.dtos.LoginUserDto;
import com.larryweinstein.battery.backend.api.dtos.RegisterUserDto;
import com.larryweinstein.battery.backend.model.User;
import com.larryweinstein.battery.backend.service.AuthService;
import com.larryweinstein.battery.backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthController {

    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authService.register(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authService.login(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expirationTime = jwtService.getExpirationTime();
        LoginResponse loginResponse = new LoginResponse(jwtToken, expirationTime);
        return ResponseEntity.ok(loginResponse);
    }
}