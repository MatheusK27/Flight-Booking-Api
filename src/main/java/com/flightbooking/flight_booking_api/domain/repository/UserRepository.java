package com.flightbooking.flight_booking_api.domain.repository;

import com.flightbooking.flight_booking_api.domain.entidade.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
