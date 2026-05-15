package com.example.telemetry.messaging.consumer;

import com.example.telemetry.processing.TelemetryProcessingEngine;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.telemetry.dto.TelemetryEventDTO;
// import com.example.telemetry.model.TelemetryEvent;
// import com.example.telemetry.repository.TelemetryRepository;
import com.example.telemetry.service.TelemetryService;
import tools.jackson.databind.ObjectMapper;

@Service
public class TelemetryConsumer {

    private final ObjectMapper objectMapper;

    private final TelemetryProcessingEngine processingEngine;

    public TelemetryConsumer(
            ObjectMapper objectMapper,
            TelemetryProcessingEngine processingEngine) {

        this.objectMapper = objectMapper;
        this.processingEngine = processingEngine;
    }

    /**
     * Listens to kafka
     * receives Json String
     *
     * converts Kafka JSON message back into Java object
     * @param message
     */
    @KafkaListener(
            topics = "telemetry-events",
            groupId = "telemetry-group")
    public void consume(String message) {

        try {
            System.out.println("=================================");
            System.out.println("Message Received: " + message);
            System.out.println("=================================");

            TelemetryEventDTO dto =
                    objectMapper.readValue(
                            message,
                            TelemetryEventDTO.class
                    );

            processingEngine.process(dto);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
