package com.example.telemetry.messaging.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.telemetry.dto.TelemetryEventDTO;
// import com.example.telemetry.model.TelemetryEvent;
// import com.example.telemetry.repository.TelemetryRepository;
import com.example.telemetry.service.TelemetryService;

@Service
public class TelemetryConsumer {

    // private final TelemetryRepository repository;
    private final TelemetryService service;

    public TelemetryConsumer(TelemetryService service) {
        this.service = service;
    }

    // @KafkaListener(topics = "telemetry-events", groupId = "telemetry-group")
    // public void consume(TelemetryEventDTO dto) {

    //     TelemetryEvent entity = new TelemetryEvent();
    //     entity.setEventId(dto.getEventId());
    //     entity.setDeviceId(dto.getDeviceId());
    //     entity.setEventType(dto.getEventType());
    //     entity.setErrorCode(dto.getErrorCode());
    //     entity.setBufferingTimeMs(dto.getBufferingTimeMs());
    //     entity.setTimestamp(dto.getTimestamp());

    //     repository.save(entity);
    // }

    @KafkaListener(topics = "telemetry-events")
    public void consume(TelemetryEventDTO dto) {
        service.processForStorage(dto);
    }
}
