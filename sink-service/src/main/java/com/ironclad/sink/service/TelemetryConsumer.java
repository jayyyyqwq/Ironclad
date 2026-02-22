package com.ironclad.sink.service;

import com.ironclad.sink.dto.TelemetryData;
import com.ironclad.sink.entity.TelemetryEntity;
import com.ironclad.sink.repository.TelemetryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TelemetryConsumer {

    private static final Logger log = LoggerFactory.getLogger(TelemetryConsumer.class);
    private final TelemetryRepository telemetryRepository;

    public TelemetryConsumer(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    @KafkaListener(topics = "telemetry-gps", groupId = "ironclad-sink-group", containerFactory = "kafkaListenerContainerFactory")
    @Transactional
    public void consumeTelemetry(List<TelemetryData> telemetryBatch) {
        log.info("Received batch of {} telemetry records", telemetryBatch.size());

        List<TelemetryEntity> entities = telemetryBatch.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        telemetryRepository.saveAll(entities);
        log.debug("Batch successfully persisted to PostgreSQL");
    }

    private TelemetryEntity mapToEntity(TelemetryData dto) {
        return new TelemetryEntity(
                dto.getDroneId(),
                dto.getGps(),
                dto.getBattery(),
                dto.getSensorMetrics(),
                dto.getTraceId());
    }
}
