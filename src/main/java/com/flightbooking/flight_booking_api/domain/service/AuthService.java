package com.flightbooking.flight_booking_api.domain.service;


import com.flightbooking.flight_booking_api.domain.dto.AuthResponse;
import com.flightbooking.flight_booking_api.domain.dto.LoginRequest;
import com.flightbooking.flight_booking_api.domain.dto.RegisterRequest;
import com.flightbooking.flight_booking_api.domain.entidade.User;
import com.flightbooking.flight_booking_api.domain.enums.UserRole;
import com.flightbooking.flight_booking_api.domain.repository.UserRepository;
import com.flightbooking.flight_booking_api.domain.security.JwtService;
import com.flightbooking.flight_booking_api.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        if(repository.existsByEmail(request.email())) {
            throw new UsernameNotFoundException("Email já cadastrado");
        }

        var user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(UserRole.PASSANGER)
                .build();
        repository.save(user);
        String token=jwtService.generateToken(user);
        return new AuthResponse(token,user.getName(),user.getEmail());
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user =  repository.findByEmail(request.email()).orElseThrow(()-> new BusinessException("User not found"));
       String token=jwtService.generateToken(user);
       return new AuthResponse(token,user.getName(),user.getEmail());
    }
}
