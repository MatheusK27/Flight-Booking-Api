package com.flightbooking.flight_booking_api;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.flightbooking.flight_booking_api.domain.dto.LoginRequest;
import com.flightbooking.flight_booking_api.domain.dto.RegisterRequest;
import com.flightbooking.flight_booking_api.domain.entidade.User;
import com.flightbooking.flight_booking_api.domain.enums.UserRole;
import com.flightbooking.flight_booking_api.domain.repository.UserRepository;
import com.flightbooking.flight_booking_api.domain.security.JwtService;
import com.flightbooking.flight_booking_api.domain.service.AuthService;
import com.flightbooking.flight_booking_api.exception.BusinessException;
import com.flightbooking.flight_booking_api.exception.ConflictException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



    @ExtendWith(MockitoExtension.class)
    class AuthServiceTest {

        @Mock
        private UserRepository userRepository;

        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private JwtService jwtService;

        @Mock
        private AuthenticationManager authenticationManager;

        @InjectMocks
        private AuthService authService;

        @Test
        @DisplayName("Deve registrar um usuário com sucesso")
        void deveRegistrarUsuarioComSucesso() {
            // ARRANGE — prepara os dados
            var request = new RegisterRequest("João", "joao@email.com", "123456");

            when(userRepository.existsByEmail("joao@email.com")).thenReturn(false);
            when(passwordEncoder.encode("123456")).thenReturn("senhaCriptografada");
            when(jwtService.generateToken(any())).thenReturn("token123");

            // ACT — executa o método
            var response = authService.register(request);

            // ASSERT — verifica o resultado
            assertNotNull(response);
            assertEquals("joao@email.com", response.email());
            assertEquals("token123", response.token());
            verify(userRepository).save(any());
        }

        @Test
        @DisplayName("Deve lançar exceção quando email já cadastrado")
        void deveLancarExcecaoQuandoEmailJaCadastrado() {
            // ARRANGE
            var request = new RegisterRequest("João", "joao@email.com", "123456");
            when(userRepository.existsByEmail("joao@email.com")).thenReturn(true);

            // ACT + ASSERT
            assertThrows(ConflictException.class, () -> authService.register(request));
            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("Deve fazer login com sucesso")
        void deveFazerLoginComSucesso() {
            // ARRANGE
            var request = new LoginRequest("joao@email.com", "123456");
            var user = User.builder()
                    .name("João")
                    .email("joao@email.com")
                    .password("senhaCriptografada")
                    .role(UserRole.PASSANGER)
                    .build();

            when(userRepository.findByEmail("joao@email.com")).thenReturn(Optional.of(user));
            when(jwtService.generateToken(any())).thenReturn("token123");

            // ACT
            var response = authService.login(request);

            // ASSERT
            assertNotNull(response);
            assertEquals("token123", response.token());
            assertEquals("joao@email.com", response.email());
        }
    }

