package com.example.telemetry.processing;

import java.time.Instant;

import com.example.telemetry.dto.TelemetryEventDTO;
import com.example.telemetry.metrics.MetricsStore;
import com.example.telemetry.model.TelemetryEvent;
import com.example.telemetry.repository.TelemetryRepository;

public class TelemetryProcessingEngine {

    private final TelemetryRepository repository;

    public TelemetryProcessingEngine(TelemetryRepository repository) {
        this.repository = repository;
    }

    public void process(TelemetryEventDTO dto) {

        // STEP 1: Validate
        if (!isValid(dto)) return;

        // STEP 2: Enrich
        ProcessedTelemetryEvent processed = enrich(dto);

        // STEP 3: Aggregate (metrics logic)
        updateMetrics(processed);

        // STEP 4: Persist structured data
        repository.save(mapToEntity(processed));
    }

    private boolean isValid(TelemetryEventDTO dto) {
        return dto.getEventId() != null
        && dto.getDeviceId() != null
        && dto.getEventType() != null;
    }

    private ProcessedTelemetryEvent enrich(TelemetryEventDTO dto) {

        ProcessedTelemetryEvent p = new ProcessedTelemetryEvent();

        p.setDeviceId(dto.getDeviceId());
        p.setEventType(dto.getEventType());
        p.setErrorCode(dto.getErrorCode());
        p.setRegion(dto.getRegion());

        // Enrichment logic
        p.setSeverity(mapSeverity(dto.getErrorCode()));

        p.setFirmwareVersion(dto.getFirmwareVersion());
        p.setProcessingTimestamp(System.currentTimeMillis());

        return p;
    }

    private String mapSeverity(String errorCode) {
        if (errorCode == null) return "INFO";
        if (errorCode.startsWith("E1")) return "LOW";
        if (errorCode.startsWith("E2")) return "MEDIUM";
        return "HIGH";
    }

    private void updateMetrics(ProcessedTelemetryEvent event) {

        MetricsStore.increment("events_total");

        if ("HIGH".equals(event.getSeverity())) {
            MetricsStore.increment("high_severity_errors");
        }

        MetricsStore.increment("region_" + event.getRegion());
    }


    private TelemetryEvent mapToEntity(ProcessedTelemetryEvent p) {

        TelemetryEvent e = new TelemetryEvent();

        e.setDeviceId(p.getDeviceId());
        e.setEventType(p.getEventType());
        e.setErrorCode(p.getErrorCode());
        e.setTimestamp(Instant.now());

        return e;
    }
}
