package com.example.telemetry.messaging.producer;

import com.example.telemetry.dto.TelemetryEventDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendEvent(String topic, TelemetryEventDTO dto) {
        try {
            String json = objectMapper.writeValueAsString(dto);
            kafkaTemplate.send(topic, json);
            System.out.println("Sent to Kafka: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        kafkaTemplate.send(topic, event);
    }

}
