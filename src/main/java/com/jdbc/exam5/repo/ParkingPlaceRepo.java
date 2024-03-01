package com.jdbc.exam5.repo;

import com.jdbc.exam5.entity.ParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingPlaceRepo extends JpaRepository<ParkingPlace, Long> {
    ParkingPlace findSalesmanByDeleteDateIsNullAndId(Long id);
    List<ParkingPlace> findAllByDeleteDateIsNull();
    ParkingPlace findByDeleteDateIsNullAndId(Long id);
}
