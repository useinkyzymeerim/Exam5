package com.jdbc.exam5.dto;

import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UnionParkingPlaceDto {
    private Long id;
    private Users user;
    private ParkingPlace parkingPlace;
}
