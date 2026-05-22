package com.example.telemetry.controller;

import com.example.telemetry.service.RealtimeMetricsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Controllers supposed to handle only Http
@RestController
@RequestMapping("/metrics")
public class MetricsController {
    private final RealtimeMetricsService realtimeMetricsService;

    public MetricsController(
            RealtimeMetricsService realtimeMetricsService
    ) {
        this.realtimeMetricsService = realtimeMetricsService;
    }

    @GetMapping("/live")
    public Map<String, String> getLiveMetrics() {
        return realtimeMetricsService.getLiveMetrics();
    }
}
