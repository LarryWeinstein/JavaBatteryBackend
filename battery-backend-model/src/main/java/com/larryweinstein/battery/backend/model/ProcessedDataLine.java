package com.larryweinstein.battery.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "processed_data_line")
@Getter
@Setter
public class ProcessedDataLine {

    @Id
    @SequenceGenerator(name = "SEQ_PROCESSED_DATA", sequenceName = "seq_processed_data", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PROCESSED_DATA")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
        ProcessedDataLine that = (ProcessedDataLine) o;
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
