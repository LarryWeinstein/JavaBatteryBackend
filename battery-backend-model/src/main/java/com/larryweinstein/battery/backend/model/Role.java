package com.larryweinstein.battery.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
