package com.example.telemetry.dto;

import lombok.Data;
import java.time.Instant;

@Data
public class TelemetryEventDTO  {
    private String eventId;
    private String deviceId;
    private String eventType;
    private String errorCode;
    private Integer bufferingTimeMs;
    private Instant timestamp;
}
