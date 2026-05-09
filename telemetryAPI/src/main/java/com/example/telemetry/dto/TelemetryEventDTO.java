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
    // new important fields
    private String firmwareVersion;
    private String appVersion;
    private String region; // geo analytics
    private String model; // device speific issues
    private String contentId; // content failure tracking
}

/*
{
  "eventId": "evt-1001",
  "deviceId": "tv-001",
  "eventType": "playback_error",
  "errorCode": "E1023",
  "bufferingTimeMs": 1200,
  "timestamp": "2026-05-10T10:00:00Z",
  "firmwareVersion": "5.2.1",
  "appVersion": "3.4.0",
  "region": "UK",
  "model": "Bravia-XR",
  "contentId": "movie-789"
}
 */
