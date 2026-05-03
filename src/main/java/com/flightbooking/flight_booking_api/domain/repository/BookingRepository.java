package com.flightbooking.flight_booking_api.domain.repository;

import com.flightbooking.flight_booking_api.domain.entidade.Booking;
import com.flightbooking.flight_booking_api.domain.entidade.Flight;
import com.flightbooking.flight_booking_api.domain.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByUser_Id(User user);
    boolean existsByFlightAndSeatNumber(Flight flight, int seatNumber);
}
