package com.ironclad.gateway.controller;

import com.ironclad.gateway.dto.TelemetryData;
import com.ironclad.gateway.service.TelemetryProducer;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/v1/telemetry")
public class TelemetryController {

    private static final Logger log = LoggerFactory.getLogger(TelemetryController.class);
    private final TelemetryProducer telemetryProducer;

    public TelemetryController(TelemetryProducer telemetryProducer) {
        this.telemetryProducer = telemetryProducer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Void> ingestTelemetry(@Valid @RequestBody TelemetryData telemetryData) {
        String traceId = UUID.randomUUID().toString();
        telemetryData.setTraceId(traceId);

        log.info("Received telemetry for drone: {} with traceId: {}", telemetryData.getDroneId(), traceId);

        return telemetryProducer.sendTelemetry(telemetryData)
                .doOnSuccess(v -> log.debug("Telemetry sent to Kafka for traceId: {}", traceId))
                .doOnError(e -> log.error("Failed to send telemetry for traceId: {}: {}", traceId, e.getMessage()));
    }
}
