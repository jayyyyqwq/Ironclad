package com.ironclad.gateway.service;

import com.ironclad.gateway.dto.TelemetryData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TelemetryProducer {

    private static final Logger log = LoggerFactory.getLogger(TelemetryProducer.class);
    private final KafkaTemplate<String, TelemetryData> kafkaTemplate;

    public TelemetryProducer(KafkaTemplate<String, TelemetryData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Mono<Void> sendTelemetry(TelemetryData telemetryData) {
        return Mono.fromFuture(() -> {
            CompletableFuture<SendResult<String, TelemetryData>> future = kafkaTemplate.send("telemetry-gps",
                    telemetryData.getDroneId(), telemetryData);
            return future;
        }).then();
    }
}
