package com.example.telemetry.controller;

import com.example.telemetry.dto.TelemetryEventDTO;
import com.example.telemetry.service.TelemetryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final TelemetryService service;

    public TelemetryController(TelemetryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> ingest(@RequestBody TelemetryEventDTO event) {
        service.processEvent(event);
        return ResponseEntity.ok("Event queued");
    }
}
