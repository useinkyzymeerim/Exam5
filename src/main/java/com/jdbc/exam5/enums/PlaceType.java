package com.jdbc.exam5.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor

public enum PlaceType {
    STANDARD("Standard"),
    FOR_DISABLED("For invalids"),
    FAMILIES("For family cars"),
    ELECTRIC_CARS("For electric cars")
    ;
    final String description;


}
