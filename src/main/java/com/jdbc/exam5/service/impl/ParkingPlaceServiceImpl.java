package com.jdbc.exam5.service.impl;

import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.repo.ParkingPlaceRepo;
import com.jdbc.exam5.service.ParkingPlaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class ParkingPlaceServiceImpl implements ParkingPlaceService {
    private final ParkingPlaceRepo parkingPlaceRepo;

    @Override
    public List<ParkingPlaceDto> getAll() {
        List<ParkingPlace> salesmen = parkingPlaceRepo.findAllByDeleteDateIsNull();

        List<ParkingPlaceDto> salesmenDto = new ArrayList<>();

        for (ParkingPlace parkingPlace : salesmen) {

            ParkingPlaceDto salesmanDto = ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .numberOfPlace(parkingPlace.getNumberOfPlace())
                    .parkingOfType(parkingPlace.getParkingOdType())
                    .build();
            salesmenDto.add(salesmanDto);
        }
        return salesmenDto;
    }

    @Override
    public ParkingPlaceDto findById(Long id) {
        ParkingPlace parkingPlace = parkingPlaceRepo.findSalesmanByDeleteDateIsNullAndId(id);

        ParkingPlaceDto parkingPlaceDto = ParkingPlaceDto.builder()
                .id(parkingPlace.getId())
                .numberOfPlace(parkingPlace.getNumberOfPlace())
                .parkingOfType(parkingPlace.getParkingOdType())
                .status(parkingPlace.getStatus())
                .build();

        return parkingPlaceDto;
    }

    @Override
    public ParkingPlaceDto create(ParkingPlaceDto parkingPlaceDto) {

        ParkingPlace parkingPlace = ParkingPlace.builder()
                .numberOfPlace(parkingPlaceDto.getNumberOfPlace())
                .parkingOdType(parkingPlaceDto.getParkingOfType())
                .status(parkingPlaceDto.getStatus())
                .build();

        try {
            parkingPlaceRepo.save(parkingPlace);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return parkingPlaceDto;
    }


    public ParkingPlaceDto update(Long id, ParkingPlaceDto parkingPlaceDto) {
        try {
            ParkingPlace parkingPlace = parkingPlaceRepo.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Manufacturer not found with id: " + id));

            ParkingPlaceDto parkingPlaceDto1 = ParkingPlaceDto.builder()
                    .numberOfPlace(parkingPlaceDto.getNumberOfPlace())
                    .parkingOfType(parkingPlaceDto.getParkingOfType())
                    .status(parkingPlaceDto.getStatus())
                    .build();

            return parkingPlaceDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        ParkingPlace parkingPlace = parkingPlaceRepo.findByDeleteDateIsNullAndId(id);

        if (parkingPlace != null) {
            parkingPlace.setDeleteDate(new Date(System.currentTimeMillis()));
            parkingPlaceRepo.save(parkingPlace);
            return true;
        } else {
            return false;

        }
    }
}
