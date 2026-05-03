package com.flightbooking.flight_booking_api.domain.repository;

import com.flightbooking.flight_booking_api.domain.entidade.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport,Long> {
    Optional<Airport> findByCode(String code);
}
