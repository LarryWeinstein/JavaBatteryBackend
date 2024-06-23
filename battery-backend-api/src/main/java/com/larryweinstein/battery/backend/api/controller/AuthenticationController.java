package com.larryweinstein.battery.backend.api.controller;

import com.larryweinstein.battery.backend.api.dtos.LoginUserDto;
import com.larryweinstein.battery.backend.api.dtos.RegisterUserDto;
import com.larryweinstein.battery.backend.model.User;
import com.larryweinstein.battery.backend.service.AuthenticationService;
import com.larryweinstein.battery.backend.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        long expirationTime = jwtService.getExpirationTime();

        LoginResponse loginResponse = new LoginResponse(jwtToken, expirationTime);

        return ResponseEntity.ok(loginResponse);
    }
}