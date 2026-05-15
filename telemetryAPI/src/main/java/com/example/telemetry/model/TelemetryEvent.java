package com.example.telemetry.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Entity
@Table(name = "telemetry_events")
@Data
public class TelemetryEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventId;

    private String deviceId;

    private String eventType;

    private String errorCode;

    private Integer bufferingTimeMs;

    private Instant timestamp;

    private String firmwareVersion;

    private String appVersion;

    private String region;

    private String model;

    private String contentId;
}
