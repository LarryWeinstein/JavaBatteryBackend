package com.larryweinstein.battery.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "SEQ_ROLE", sequenceName = "seq_role", allocationSize = 1)
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SEQ_ROLE")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    private ERole name;

    public Role(){

    }

    public Role(ERole name){
        this.name = name;
    }
}
