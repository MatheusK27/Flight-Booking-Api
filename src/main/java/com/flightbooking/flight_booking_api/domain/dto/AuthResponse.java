package com.flightbooking.flight_booking_api.domain.dto;

public record AuthResponse( String token,
                            String name,
                            String email) {
}
