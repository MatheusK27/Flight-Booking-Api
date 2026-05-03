package com.flightbooking.flight_booking_api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightResponse(Long id,
                             String flightNumber,
                             String origin,
                             String destination,
                             LocalDateTime departureTime,
                             LocalDateTime arrivalTime,
                             Integer availableSeats,
                             BigDecimal price,
                             String status) {
}
