package com.jdbc.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@AllArgsConstructor
public enum ParkingPlaceStatus {
    Free ("FREE"),
    OCCUPIED("OCCUPIED");
    private final String DESCRIPTION;

}
