package com.larryweinstein.battery.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames =  "username"),
@UniqueConstraint(columnNames = "email")})
@Getter
@Setter
public class User {
    @Id
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "seq_user", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "username", length = 20)
    private String username;

    @NotBlank
    @Column(name = "email", length = 50)
    @Email
    private String email;

    @NotBlank
    @Column(name = "password", length = 30)
    private String password;

    public User(){

    }

   public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
   }

}
