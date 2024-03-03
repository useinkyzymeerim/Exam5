package com.jdbc.exam5.controller;

import com.jdbc.exam5.dto.AllParkingPlaceDto;
import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.dto.UnionParkingPlaceDto;
import com.jdbc.exam5.entity.UnionParkingPlace;
import com.jdbc.exam5.service.ParkingPlaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/parking-spots")

public class ParkingPlaceController {
    private final ParkingPlaceService parkingPlaceService;
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Created parking place successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParkingPlaceDto.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to add place to database")
    })
    @Operation(summary = "This road creates parking place")
    @PostMapping("/create")
    public ResponseEntity<ParkingPlaceDto> create(@RequestBody ParkingPlaceDto createParking){
        ParkingPlaceDto createParkingPlaceDto = parkingPlaceService.create(createParking);
        return ResponseEntity.status(HttpStatus.CREATED).body(createParkingPlaceDto);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parking place exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ParkingPlaceDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking place")
    })
    @Operation(summary = "This road can find parking place by id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ParkingPlaceDto> getById(@PathVariable Long id){
        ParkingPlaceDto parkingPlaceDto = parkingPlaceService.findById(id);
        if(parkingPlaceDto != null){
            return new ResponseEntity<>(parkingPlaceDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parking places is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllParkingPlaceDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking places")
    })
    @Operation(summary = "This road find all parking place")
    @GetMapping("/all")
    public ResponseEntity<List<AllParkingPlaceDto>> getAll(){
        List<AllParkingPlaceDto> allParkingPlaceDtoList = parkingPlaceService.getAll();
        if(!allParkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(allParkingPlaceDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Parking place was deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any parking places with this id")
    })
    @Operation(summary = "This road deletes parking place by id")
    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        boolean deleted = parkingPlaceService.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Entity with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with id " + id + " not found");
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The parking place was updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to renew the parking space")
    })
    @Operation(summary = "This road update parking")
    @PutMapping("/update")
    public ResponseEntity<ParkingPlaceDto> update(@RequestBody ParkingPlaceDto placeDto) {
        ParkingPlaceDto updatedParkingPlace = parkingPlaceService.update(placeDto);
        if (updatedParkingPlace != null) {
            return ResponseEntity.ok(updatedParkingPlace);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Reserve created successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to add reservation to database")
    })
    @Operation(summary = "This road creates reservation")
    @PostMapping("/reserveParkingPlace")
    public ResponseEntity<String> reservePlace(@RequestBody UnionParkingPlaceDto reservePlace){
        try{
            return new ResponseEntity<>(parkingPlaceService.reservePlace(reservePlace), HttpStatus.CREATED);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "You have release place successfully ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to release place")
    })
    @Operation(summary = "This road is using for releasing parking place")
    @PutMapping("/release")
    public ResponseEntity<String> takePlace(@RequestBody UnionParkingPlaceDto takePlace){
        try {
            return new ResponseEntity<>(parkingPlaceService.releaseParkingPlace(takePlace), HttpStatus.CREATED);
        }catch (RuntimeException runtimeException){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
