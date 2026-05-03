package com.flightbooking.flight_booking_api.domain.dto;

import com.flightbooking.flight_booking_api.domain.enums.SeatClass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookingRequest(@NotNull Long flightId,
                             @NotBlank String seatNumber,
                             @NotNull SeatClass seatClass) {
}
