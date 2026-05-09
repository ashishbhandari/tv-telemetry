package com.example.telemetry.metrics;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetricsStore {
    
    private static final Map<String, Long> metrics = new ConcurrentHashMap<>();

        public static void increment(String key) {
            metrics.put(key, metrics.getOrDefault(key, 0L) + 1);
        }

        public static Map<String, Long> getAll() {
            return metrics;
        }
}
