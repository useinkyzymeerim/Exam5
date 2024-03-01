package com.jdbc.exam5.dto;

import com.jdbc.exam5.entity.Users;
import com.jdbc.exam5.enums.ParkingPlaceStatus;
import com.jdbc.exam5.enums.PlaceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingPlaceDto {
    private Long id;
    private Integer numberOfPlace;
    private PlaceType parkingOfType;
    private ParkingPlaceStatus status;
    private Users users;

}
