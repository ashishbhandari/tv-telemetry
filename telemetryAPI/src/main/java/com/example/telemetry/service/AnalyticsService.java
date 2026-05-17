package com.example.telemetry.service;

import com.example.telemetry.repository.TelemetryRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {
    private final TelemetryRepository repository;

    public AnalyticsService(TelemetryRepository repository) {
        this.repository = repository;
    }

    public long totalEvents() {
        return repository.count();
    }

    public long playbackErrors() {
        return repository.countByEventType("playback_error");
    }

    public Map<String, Long> errorsByRegion() {
        return convert(repository.countEventsByRegion());
    }

    public Map<String, Long> errorsByFirmware() {
        return convert(repository.countErrorsByFirmware());
    }

    public Map<String, Long> topErrorCodes() {
        return convert(repository.topErrorCodes());
    }

    private Map<String, Long> convert(List<Object[]> rows) {
        Map<String, Long> result = new LinkedHashMap<>();

        for (Object[] row : rows) {
            String key = row[0] == null ? "UNKNOWN" : row[0].toString();
            Long value = (Long) row[1];
            result.put(key, value);
        }

        return result;
    }
}
