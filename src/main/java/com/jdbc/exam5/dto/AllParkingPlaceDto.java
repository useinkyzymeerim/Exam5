package com.jdbc.exam5.dto;

import com.jdbc.exam5.enums.ParkingPlaceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllParkingPlaceDto {
    private String spotNumber;
    private ParkingPlaceStatus status;

}
