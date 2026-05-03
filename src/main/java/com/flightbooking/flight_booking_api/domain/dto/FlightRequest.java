package com.flightbooking.flight_booking_api.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightRequest(@NotBlank String flightNumber,
                            @NotNull Long originId,
                            @NotNull Long destinationId,
                            @NotNull LocalDateTime departureTime,
                            @NotNull LocalDateTime arrivalTime,
                            @NotNull Integer totalSeats,
                            @NotNull BigDecimal price) {
}
