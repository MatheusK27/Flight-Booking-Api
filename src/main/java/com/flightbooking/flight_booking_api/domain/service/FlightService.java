package com.flightbooking.flight_booking_api.domain.service;

import com.flightbooking.flight_booking_api.domain.dto.FlightRequest;
import com.flightbooking.flight_booking_api.domain.dto.FlightResponse;
import com.flightbooking.flight_booking_api.domain.entidade.Flight;
import com.flightbooking.flight_booking_api.domain.enums.FlightStatus;
import com.flightbooking.flight_booking_api.domain.repository.AirportRepository;
import com.flightbooking.flight_booking_api.domain.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {


    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public FlightResponse create(FlightRequest request) {
        var origin = airportRepository.findById(request.originId())
                .orElseThrow(() -> new RuntimeException("Aeroporto de origem não encontrado"));

        var destination = airportRepository.findById(request.destinationId())
                .orElseThrow(() -> new RuntimeException("Aeroporto de destino não encontrado"));

        var flight = Flight.builder()
                .flightNumber(request.flightNumber())
                .origin(origin)
                .destination(destination)
                .departureTime(request.departureTime())
                .arrivalTime(request.arrivalTime())
                .totalSeats(request.totalSeats())
                .availableSeats(request.totalSeats())
                .price(request.price())
                .status(FlightStatus.SCHEDULED)
                .build();
        flightRepository.save(flight);
        return toResponse(flight);

    }

    public List<FlightResponse> search(String originCode, String destinationCode, LocalDateTime date) {
        var origin = airportRepository.findByCode(originCode)
                .orElseThrow(() -> new RuntimeException("Aeroporto de origem não encontrado"));
        var destination = airportRepository.findByCode(destinationCode)
                .orElseThrow(() -> new RuntimeException("Aeroporto de destino não encontrado"));
        return flightRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                        origin, destination, date.toLocalDate().atStartOfDay(), date.toLocalDate().atTime(23, 59))
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<FlightResponse> findAll() {
        return flightRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();

    }

    private FlightResponse toResponse(Flight flight) {
        return new FlightResponse(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getOrigin().getCity(),
                flight.getDestination().getCity(),
                flight.getDepartureTime(),
                flight.getArrivalTime(),
                flight.getAvailableSeats(),
                flight.getPrice(),
                flight.getStatus().name()
        );
    }
}
