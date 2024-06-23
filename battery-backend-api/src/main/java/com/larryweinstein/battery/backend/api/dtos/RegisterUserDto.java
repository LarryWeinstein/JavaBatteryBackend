package com.larryweinstein.battery.backend.api.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String username;
    private String email;
    private String password;
}
