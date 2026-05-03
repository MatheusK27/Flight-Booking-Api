package com.flightbooking.flight_booking_api.domain.entidade;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "airports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airport {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    private String city;
    private String country;



}
