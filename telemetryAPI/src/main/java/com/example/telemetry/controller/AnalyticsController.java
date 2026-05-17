package com.example.telemetry.controller;

import com.example.telemetry.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/total-events")
    public long totalEvents() {
        return service.totalEvents();
    }

    @GetMapping("/playback-errors")
    public long playbackErrors() {
        return service.playbackErrors();
    }

    @GetMapping("/errors-by-region")
    public Object errorsByRegion() {
        return service.errorsByRegion();
    }

    @GetMapping("/errors-by-firmware")
    public Object errorsByFirmware() {
        return service.errorsByFirmware();
    }

    @GetMapping("/top-error-codes")
    public Object topErrorCodes() {
        return service.topErrorCodes();
    }
}
