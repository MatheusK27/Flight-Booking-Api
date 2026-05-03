package com.flightbooking.flight_booking_api.domain.entidade;


import com.flightbooking.flight_booking_api.domain.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import java.time.LocalDateTime;

@Entity
@Table(name="flights")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Flight {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name= "flight_number")
    private String flightNumber;

    @ManyToOne
    private Airport origin;

    @ManyToOne
    private Airport destination;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name="arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "total_seats")
    private Integer totalSeats;

    @Column(name="available_seats")
    private Integer availableSeats;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;
}
