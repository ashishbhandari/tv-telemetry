package com.example.telemetry.service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Real time metric via redis
 * Redis implementation hidden here.
 */
@Service
public class RealtimeMetricsService {

    private final StringRedisTemplate redisTemplate;

    public RealtimeMetricsService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementPlaybackErrors() {

        redisTemplate.opsForValue()
                .increment("playback_errors_total");
    }

    public void incrementRegionErrors(String region) {

        redisTemplate.opsForValue()
                .increment("region:" + region + ":errors");
    }

    public Map<String, String> getLiveMetrics() {

        return Map.of(
                "playback_errors_total",
                redisTemplate.opsForValue()
                        .get("playback_errors_total"),

                "region_UK_errors",
                redisTemplate.opsForValue()
                        .get("region:UK:errors")
        );
    }
}
