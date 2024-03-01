package com.jdbc.exam5.repo;

import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.enums.ParkingPlaceStatus;
import com.jdbc.exam5.enums.PlaceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ParkingPlaceRepo extends JpaRepository<ParkingPlace, Long> {


    @Query("select p from  ParkingPlace p " +
            "where p.status = com.jdbc.exam5.enums.ParkingPlaceStatus.Free and p.id = :id")
    ParkingPlace findAvailablePlaceById(@Param("id")Long id);


}
