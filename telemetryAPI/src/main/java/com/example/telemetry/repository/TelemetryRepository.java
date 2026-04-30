package com.example.telemetry.repository;

import com.example.telemetry.model.TelemetryEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelemetryRepository extends JpaRepository<TelemetryEvent, String> {
}