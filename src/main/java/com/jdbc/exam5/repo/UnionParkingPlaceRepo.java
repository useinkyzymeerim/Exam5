package com.jdbc.exam5.repo;

import com.jdbc.exam5.entity.UnionParkingPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnionParkingPlaceRepo extends JpaRepository<UnionParkingPlace, Long> {
}
