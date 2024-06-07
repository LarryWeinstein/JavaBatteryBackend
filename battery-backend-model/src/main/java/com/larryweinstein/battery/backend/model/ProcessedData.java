package com.larryweinstein.battery.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "processed_data")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProcessedData {
    @Id
    @SequenceGenerator(name = "SEQ_DATA", sequenceName = "seq_data", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DATA")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "battery_id", nullable = false)
    private Battery battery;

    @Column(name = "cycle_number")
    private Integer cycleNumber;

    @Column(name = "charge_capacity")
    private Double chargeCapacity;

    @Column(name = "discharge_capacity")
    private Double dischargeCapacity;

    public ProcessedData(Battery battery){
        this.battery = battery;
    }

    public ProcessedData(Battery battery, Integer cycleNumber, Double chargeCapacity, Double dischargeCapacity){
        this.battery = battery;
        this.cycleNumber = cycleNumber;
        this.chargeCapacity = chargeCapacity;
        this.dischargeCapacity = dischargeCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedData that = (ProcessedData) o;
        return id != null & Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ProcessedData{" +
                "id=" + id +
                ", battery=" + battery +
                ", cycleNumber=" + cycleNumber +
                ", chargeCapacity=" + chargeCapacity +
                ", dischargeCapacity=" + dischargeCapacity +
                '}';
    }
}
