package com.example.telemetry.service;

import com.example.telemetry.dto.TelemetryEventDTO;
import com.example.telemetry.model.TelemetryEvent;
import com.example.telemetry.repository.TelemetryRepository;
import org.springframework.stereotype.Service;

@Service
public class TelemetryService {

    private final TelemetryRepository repository;

    public TelemetryService(TelemetryRepository repository) {
        this.repository = repository;
    }

    public void processEvent(TelemetryEventDTO dto) {

        TelemetryEvent event = new TelemetryEvent();
        event.setEventId(dto.getEventId());
        event.setDeviceId(dto.getDeviceId());
        event.setEventType(dto.getEventType());
        event.setErrorCode(dto.getErrorCode());
        event.setBufferingTimeMs(dto.getBufferingTimeMs());
        event.setTimestamp(dto.getTimestamp());

        repository.save(event);
    }
}