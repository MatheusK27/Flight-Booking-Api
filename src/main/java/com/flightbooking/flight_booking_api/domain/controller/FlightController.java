package com.flightbooking.flight_booking_api.domain.controller;


import com.flightbooking.flight_booking_api.domain.dto.FlightRequest;
import com.flightbooking.flight_booking_api.domain.dto.FlightResponse;
import com.flightbooking.flight_booking_api.domain.service.FlightService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @Transactional
    @PostMapping
    public ResponseEntity<FlightResponse> create(@RequestBody @Valid FlightRequest flightRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.create(flightRequest));
    }

    @Transactional
    @GetMapping
    public ResponseEntity<List<FlightResponse>> findAll() {
        return ResponseEntity.ok(flightService.findAll());
    }

    @Transactional
    @GetMapping("/search")
    public ResponseEntity<List<FlightResponse>> search(@RequestParam String origin,
                                                       @RequestParam String destinantion,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime date) {

        return ResponseEntity.ok(flightService.search(origin,destinantion,date));

    }
}
