package com.example.telemetry.repository;

import com.example.telemetry.model.TelemetryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TelemetryRepository extends JpaRepository<TelemetryEvent, Long> {

    // telemetry insights through REST APIs:
    //    Analytics API Layer
    long countByEventType(String eventType);

    @Query("SELECT t.region, COUNT(t) FROM TelemetryEvents t GROUP BY t.region")
    List<Object[]> countEventsByRegion();

    @Query("SELECT t.firmwareVersion, COUNT(t) FROM TelemetryEvents t WHERE t.eventType = 'playback_error' GROUP BY t.firmwareVersion")
    List<Object[]> countErrorsByFirmware();

    @Query("SELECT t.errorCode, COUNT(t) FROM TelemetryEvents t WHERE t.errorCode IS NOT NULL GROUP BY t.errorCode ORDER BY COUNT(t) DESC")
    List<Object[]> topErrorCodes();
}