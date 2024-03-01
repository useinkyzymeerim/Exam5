package com.jdbc.exam5.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jdbc.exam5.enums.ParkingPlaceStatus;
import com.jdbc.exam5.enums.PlaceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Builder
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String spotNumber;

    @Enumerated(value = EnumType.STRING)
    private PlaceType parkingOfType;

    @Enumerated(value = EnumType.STRING)
    private ParkingPlaceStatus status;


}
