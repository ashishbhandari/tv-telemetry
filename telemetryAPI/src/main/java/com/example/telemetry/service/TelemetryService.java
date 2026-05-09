package com.example.telemetry.service;

import com.example.telemetry.dto.TelemetryEventDTO;
import com.example.telemetry.messaging.producer.KafkaProducerService;
import com.example.telemetry.model.TelemetryEvent;
import com.example.telemetry.repository.TelemetryRepository;

import org.springframework.stereotype.Service;

@Service
public class TelemetryService {

    private final TelemetryRepository repository;
    
    private final KafkaProducerService producer;

    public TelemetryService(KafkaProducerService producer, TelemetryRepository repository) {
        this.producer = producer;
        this.repository = repository;   
    }

    // Called by Controller (API path)
    public void processEvent(TelemetryEventDTO dto) {
        // producer.sendEvent("telemetry-events", dto.toString());
    }

    // Called by Kafka Consumer (storage path)
    public void processForStorage(TelemetryEventDTO dto) {
        TelemetryEvent entity = mapToEntity(dto);
        repository.save(entity);
    }

    // Centralize mapping so both paths stay consistent
    private TelemetryEvent mapToEntity(TelemetryEventDTO dto) {
        TelemetryEvent e = new TelemetryEvent();
        e.setEventId(dto.getEventId());
        e.setDeviceId(dto.getDeviceId());
        e.setEventType(dto.getEventType());
        e.setErrorCode(dto.getErrorCode());
        e.setBufferingTimeMs(dto.getBufferingTimeMs());
        e.setTimestamp(dto.getTimestamp());
        return e;
    }
    

}