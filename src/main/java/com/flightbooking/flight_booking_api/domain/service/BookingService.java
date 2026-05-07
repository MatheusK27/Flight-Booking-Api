package com.flightbooking.flight_booking_api.domain.service;


import com.flightbooking.flight_booking_api.domain.dto.BookingRequest;
import com.flightbooking.flight_booking_api.domain.dto.BookingResponse;
import com.flightbooking.flight_booking_api.domain.entidade.Booking;
import com.flightbooking.flight_booking_api.domain.enums.BookingStatus;
import com.flightbooking.flight_booking_api.domain.repository.BookingRepository;
import com.flightbooking.flight_booking_api.domain.repository.FlightRepository;
import com.flightbooking.flight_booking_api.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final FlightRepository flightRepository;
    private final  UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public BookingResponse create(BookingRequest request,String email) {
        var user= userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        var flight = flightRepository.findById(request.flightId()).orElseThrow(()-> new RuntimeException("Voo não encontrado"));

        if(flight.getAvailableSeats()<=0){
            throw new RuntimeException ("Não há assentos disponíveis!");

        }
        if (bookingRepository.existsByFlightAndSeatNumber(flight, request.seatNumber())){
            throw new RuntimeException("Assento já ocupado!");
        }

        flight.setAvailableSeats(flight.getAvailableSeats()-1);
        flightRepository.save(flight);

        var booking = Booking.builder()
                .user(user)
                .flight(flight)
                .seatNumber(request.seatNumber())
                .seatClass(request.seatClass())
                .status(BookingStatus.CONFIRMED)
                .totalPrice(flight.getPrice())
                .build();

        bookingRepository.save(booking);
        return toResponse(booking);

    }

    public List<BookingResponse>findByUser(String email){
        var user= userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));

        return bookingRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public BookingResponse cancel(Long id, String email){
        var booking = bookingRepository.findById(id).orElseThrow(()->new RuntimeException("Reserva não encontrada"));

        if(!booking.getUser().getEmail().equals(email)){
            throw new RuntimeException("Usuário não tem permissão pra cancelar");

        }
        if(booking.getStatus()==BookingStatus.CANCELLED){
            throw new RuntimeException("Reserva já cancelada!");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        booking.setCancelledAt(LocalDateTime.now());

        var flight= booking.getFlight();
        flight.setAvailableSeats(flight.getAvailableSeats()+1);
        flightRepository.save(flight);

        bookingRepository.save(booking);
        return toResponse(booking);

    }

    private BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getFlight().getFlightNumber(),
                booking.getFlight().getOrigin().getCity(),
                booking.getFlight().getDestination().getCity(),
                booking.getSeatNumber(),
                booking.getSeatClass().name(),
                booking.getStatus().name(),
                booking.getTotalPrice(),
                booking.getBookedAt()
        );
    }
}
