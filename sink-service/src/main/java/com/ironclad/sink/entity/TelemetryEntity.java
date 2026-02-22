package com.ironclad.sink.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "telemetry_records")
public class TelemetryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String droneId;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Double> gps;

    private Integer battery;

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> sensorMetrics;

    private String traceId;

    private LocalDateTime receivedAt;

    public TelemetryEntity() {
    }

    public TelemetryEntity(String droneId, Map<String, Double> gps, Integer battery, Map<String, Object> sensorMetrics,
            String traceId) {
        this.droneId = droneId;
        this.gps = gps;
        this.battery = battery;
        this.sensorMetrics = sensorMetrics;
        this.traceId = traceId;
    }

    @PrePersist
    protected void onCreate() {
        receivedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public Map<String, Double> getGps() {
        return gps;
    }

    public void setGps(Map<String, Double> gps) {
        this.gps = gps;
    }

    public Integer getBattery() {
        return battery;
    }

    public void setBattery(Integer battery) {
        this.battery = battery;
    }

    public Map<String, Object> getSensorMetrics() {
        return sensorMetrics;
    }

    public void setSensorMetrics(Map<String, Object> sensorMetrics) {
        this.sensorMetrics = sensorMetrics;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public LocalDateTime getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(LocalDateTime receivedAt) {
        this.receivedAt = receivedAt;
    }
}
