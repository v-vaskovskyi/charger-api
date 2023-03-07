package com.dcs.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(indexes = @Index(name = "vehicleIdIdx", columnList = "vehicleId"))
public class ChargeDetailRecord {
    @Id
    @SequenceGenerator(
            name="cdr_sequence",
            sequenceName = "cdr_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cdr_sequence"
    )
    @Column(name = "id")
    private long sessionId;
    private String vehicleId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double cost;

    public ChargeDetailRecord() {
    }

    public ChargeDetailRecord(String vehicleId,
                              LocalDateTime startTime,
                              LocalDateTime endTime,
                              Double cost) {
        this.vehicleId = vehicleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.cost = cost;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
