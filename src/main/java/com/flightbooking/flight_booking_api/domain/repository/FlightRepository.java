package com.flightbooking.flight_booking_api.domain.repository;

import com.flightbooking.flight_booking_api.domain.entidade.Airport;
import com.flightbooking.flight_booking_api.domain.entidade.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByOriginAndDestinationAndDepartureTimeBetween(
            Airport origin, Airport destination, LocalDateTime start, LocalDateTime end
    );
}
