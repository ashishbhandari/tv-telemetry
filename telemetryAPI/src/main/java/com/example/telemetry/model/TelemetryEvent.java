package com.example.telemetry.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Table(name = "telemetry_events")
@Data
public class TelemetryEvent {

    @Id
    private String eventId;

    private String deviceId;
    private String eventType;
    private String errorCode;
    private Integer bufferingTimeMs;
    private Instant timestamp;
}
