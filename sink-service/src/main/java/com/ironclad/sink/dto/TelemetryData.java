package com.ironclad.sink.dto;

import java.util.Map;

public class TelemetryData {
    private String droneId;
    private Map<String, Double> gps;
    private Integer battery;
    private Map<String, Object> sensorMetrics;
    private String traceId;

    public TelemetryData() {
    }

    public TelemetryData(String droneId, Map<String, Double> gps, Integer battery, Map<String, Object> sensorMetrics,
            String traceId) {
        this.droneId = droneId;
        this.gps = gps;
        this.battery = battery;
        this.sensorMetrics = sensorMetrics;
        this.traceId = traceId;
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
}
