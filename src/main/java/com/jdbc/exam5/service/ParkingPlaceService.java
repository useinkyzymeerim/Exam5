package com.jdbc.exam5.service;

import com.jdbc.exam5.dto.ParkingPlaceDto;


import java.util.List;

public interface ParkingPlaceService {
   List<ParkingPlaceDto> getAll();

   ParkingPlaceDto findById(Long id);

   ParkingPlaceDto create (ParkingPlaceDto parkingPlace);

   Boolean deleteById(Long id);
   ParkingPlaceDto update(Long id, ParkingPlaceDto parkingPlaceDto);
}