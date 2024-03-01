package com.jdbc.exam5.controller;

import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.service.ParkingPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/Parking/")

public class ParkingPlaceController {
    private final ParkingPlaceService parkingPlaceService;

    @PostMapping("create")
    public ResponseEntity<ParkingPlaceDto> create(@RequestBody ParkingPlaceDto parkingPlaceDto){

        ParkingPlaceDto parkingPlaceDto1 = parkingPlaceService.create(parkingPlaceDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(parkingPlaceDto1);
    }
    @GetMapping("getById/{id}")
    public ResponseEntity<ParkingPlaceDto> getById(@PathVariable Long id){
        ParkingPlaceDto parkingPlaceDto = parkingPlaceService.findById(id);
        if(parkingPlaceDto != null){
            return new ResponseEntity<>(parkingPlaceDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("-spots")
    public ResponseEntity<List<ParkingPlaceDto>> getAll(){
        List<ParkingPlaceDto> parkingPlaceDtoList = parkingPlaceService.getAll();
        if(!parkingPlaceDtoList.isEmpty()){
            return new ResponseEntity<>(parkingPlaceDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<Boolean> delete(@RequestParam Long id){
        if(parkingPlaceService.deleteById(id)){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }
}
