package com.ironclad.gateway.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic telemetryGpsTopic() {
        return TopicBuilder.name("telemetry-gps")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
