package com.jdbc.exam5.service;

import com.jdbc.exam5.dto.AllParkingPlaceDto;
import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.dto.UnionParkingPlaceDto;
import com.jdbc.exam5.entity.UnionParkingPlace;
import jakarta.persistence.EntityNotFoundException;


import java.util.List;

public interface ParkingPlaceService {
   List<AllParkingPlaceDto> getAll();

   ParkingPlaceDto findById(Long id);

   ParkingPlaceDto create(ParkingPlaceDto createParkingPlaceDto);

   boolean deleteById(Long id);
   String reservePlace(UnionParkingPlaceDto reservePlace) throws RuntimeException;
   ParkingPlaceDto update(ParkingPlaceDto placeDto);
   String releaseParkingPlace(UnionParkingPlaceDto takePlace) throws RuntimeException;
}