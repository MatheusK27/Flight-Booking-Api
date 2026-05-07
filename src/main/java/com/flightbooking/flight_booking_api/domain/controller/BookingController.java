package com.flightbooking.flight_booking_api.domain.controller;


import com.flightbooking.flight_booking_api.domain.dto.BookingRequest;
import com.flightbooking.flight_booking_api.domain.dto.BookingResponse;
import com.flightbooking.flight_booking_api.domain.service.BookingService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;


    @PostMapping
    @Transactional
    public ResponseEntity<BookingResponse> create(@RequestBody @Valid BookingRequest bookingRequest) {
        return ResponseEntity.ok().build();
    }


    @GetMapping
    @Transactional

    public ResponseEntity<List<BookingResponse>> findByUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bookingService.findByUser(userDetails.getUsername()));
    }

    @PatchMapping("/{id}/cancel")
    @Transactional

    public ResponseEntity<BookingResponse> cancel(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(bookingService.cancel(id, userDetails.getUsername()));

    }
}