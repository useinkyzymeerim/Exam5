package com.jdbc.exam5.service.impl;

import com.jdbc.exam5.dto.AllParkingPlaceDto;
import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.dto.UnionParkingPlaceDto;
import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.entity.UnionParkingPlace;
import com.jdbc.exam5.entity.Users;
import com.jdbc.exam5.enums.ParkingPlaceStatus;
import com.jdbc.exam5.repo.ParkingPlaceRepo;
import com.jdbc.exam5.repo.UnionParkingPlaceRepo;
import com.jdbc.exam5.service.ParkingPlaceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
    @RequiredArgsConstructor
    @Service
    @Slf4j
    public class ParkingPlaceServiceImpl implements ParkingPlaceService {
        private final ParkingPlaceRepo parkingPlaceRepo;
        private final UnionParkingPlaceRepo unionParkingPlaceRepo;


    @Override
    public List<AllParkingPlaceDto> getAll() {
            List<ParkingPlace> parkingPlace = parkingPlaceRepo.findAll();

            List<AllParkingPlaceDto> parkingPlaceDtos = new ArrayList<>();
            for(ParkingPlace parkingPlace1 : parkingPlace){
                AllParkingPlaceDto parkingPlaceDto = AllParkingPlaceDto.builder()
                        .spotNumber(parkingPlace1.getSpotNumber())
                        .status(parkingPlace1.getStatus())
                        .build();
                parkingPlaceDtos.add(parkingPlaceDto);
            }
            return parkingPlaceDtos;
        }

    @Override
    public ParkingPlaceDto findById(Long id) {

            ParkingPlace parkingPlace = parkingPlaceRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(""));

            return ParkingPlaceDto.builder()
                    .id(parkingPlace.getId())
                    .spotNumber(parkingPlace.getSpotNumber())
                    .parkingOfType(parkingPlace.getParkingOfType())
                    .status(parkingPlace.getStatus())
                    .build();
        }

        @Override
    public ParkingPlaceDto create(ParkingPlaceDto createParkingPlaceDto) {

        ParkingPlace parkingPlace = ParkingPlace.builder()
                .spotNumber(createParkingPlaceDto.getSpotNumber())
                .parkingOfType(createParkingPlaceDto.getParkingOfType())
                .status(createParkingPlaceDto.getStatus())
                .build();

        try {
            parkingPlaceRepo.save(parkingPlace);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return createParkingPlaceDto;
    }


        @Override
        public ParkingPlaceDto update(ParkingPlaceDto placeDto) {
            ParkingPlace parkingPlace = parkingPlaceRepo.findById(placeDto.getId())
                    .orElseThrow(()-> new EntityNotFoundException("Parking place not found with id: " + placeDto.getId()));
            if (placeDto.getSpotNumber() != null){
                parkingPlace.setSpotNumber(placeDto.getSpotNumber());
            }
            if (placeDto.getStatus() != null){
                parkingPlace.setStatus(placeDto.getStatus());
            }
            if (placeDto.getParkingOfType() != null){
                parkingPlace.setParkingOfType(placeDto.getParkingOfType());
            }
            parkingPlaceRepo.save(parkingPlace);
            return placeDto;
        }

    @Override
    public boolean deleteById(Long id) {
        if (parkingPlaceRepo.existsById(id)) {
            parkingPlaceRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

        @Override
        public String reservePlace(UnionParkingPlaceDto reservePlace) throws RuntimeException{
            Long orderId;
            if (parkingPlaceRepo.findById(reservePlace.getUser().getId()).isPresent() &&
                    parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId()) != null) {

                try {
                    UnionParkingPlace reserve = UnionParkingPlace.builder()
                            .parkingPlace(reservePlace.getParkingPlace())
                            .user(reservePlace.getUser())
                            .build();

                    orderId = unionParkingPlaceRepo.save(reserve).getId();
                } catch (Exception e) {
                    throw new RuntimeException("Some thing went wrong!");
                }
                ParkingPlace reservedParkingPlace =
                        parkingPlaceRepo.findAvailablePlaceById(reservePlace.getParkingPlace().getId());

                reservedParkingPlace.setStatus(ParkingPlaceStatus.OCCUPIED);
                parkingPlaceRepo.save(reservedParkingPlace);
                return "Your reservation is successful. Remember id for Confirming or Canceling " +
                        "ID: " + orderId;
            } else throw new RuntimeException("Some thing went wrong!");
        }


        @Override
        public String releaseParkingPlace(UnionParkingPlaceDto takePlace) throws RuntimeException{
            Long orderId;
            if (parkingPlaceRepo.findById(takePlace.getUser().getId()).isPresent() &&
                    parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId()) != null) {

                try {
                    UnionParkingPlace reserve = UnionParkingPlace.builder()
                            .parkingPlace(takePlace.getParkingPlace())
                            .user(takePlace.getUser())
                            .build();

                    orderId = unionParkingPlaceRepo.save(reserve).getId();
                } catch (Exception e) {
                    throw new RuntimeException("Some thing went wrong");
                }
                ParkingPlace takeParkingPlace =
                        parkingPlaceRepo.findAvailablePlaceById(takePlace.getParkingPlace().getId());

                takeParkingPlace.setStatus(ParkingPlaceStatus.OCCUPIED);
                parkingPlaceRepo.save(takeParkingPlace);

                return "Your interaction is successful. Remember id for Releasing Parking place " +
                        "ID: " + orderId;
            } else throw new RuntimeException("Some thing went wrong!");
        }



    }
