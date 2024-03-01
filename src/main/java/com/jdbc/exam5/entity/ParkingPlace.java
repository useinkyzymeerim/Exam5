package com.jdbc.exam5.entity;

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
    private Integer numberOfPlace;


    private PlaceType parkingOdType;


    private ParkingPlaceStatus status;

    private Date deleteDate;

    @ManyToOne
    private Users users;

}
