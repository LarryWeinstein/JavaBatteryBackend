package com.larryweinstein.battery.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "processed_data")
@Getter
@Setter
public class ProcessedData {

    @Id
    @SequenceGenerator(name = "SEQ_PROCESSED_DATA", sequenceName = "seq_processed_data", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSED_DATA")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "battery_id", nullable = false)
    private Battery battery;

    @Column(name = "cycle_number")
    private Integer cycleNumber;

    @Column(name = "charge_capacity")
    private Double chargeCapacity;

    @Column(name = "discharge_capacity")
    private Double dischargeCapacity;

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
