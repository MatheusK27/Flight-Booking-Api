package com.flightbooking.flight_booking_api.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingResponse(Long id,
                              String flightNumber,
                              String origin,
                              String destination,
                              String seatNumber,
                              String seatClass,
                              String status,
                              BigDecimal totalPrice,
                              LocalDateTime bookedAt) {
}
