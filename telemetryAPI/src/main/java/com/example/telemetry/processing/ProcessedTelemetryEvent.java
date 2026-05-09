package com.example.telemetry.processing;

import lombok.Data;

/**
 * 👉 This is NOT DB model
👉 This is NOT DTO
👉 This is internal processing representation
 */
@Data
public class ProcessedTelemetryEvent {

    private String deviceId;
    private String eventType;
    private String errorCode;
    private String firmwareVersion;
    private String region;

    private boolean valid;
    private String severity;

    private long processingTimestamp;
}
