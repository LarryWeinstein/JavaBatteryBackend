package com.larry;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "battery")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Battery {
    @Id
    @SequenceGenerator(name = "SEQ_BATTERY", sequenceName = "seq_battery", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BATTERY")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "date_updated")
    private LocalDate lastUpdated;

    @OneToMany(mappedBy = "battery")
    private List<ProcessedData> processedData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return id != null && Objects.equals(id, battery.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Battery{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", processedData=" + processedData +
                '}';
    }
}
