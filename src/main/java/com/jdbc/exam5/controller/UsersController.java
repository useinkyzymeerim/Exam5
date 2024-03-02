package com.jdbc.exam5.controller;
import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.dto.UsersDto;
import com.jdbc.exam5.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/users")
public class UsersController {
    private final UsersService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "User has been created successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersDto.class))}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Failed to add user to database")
    })
    @Operation(summary = "This road creates user")
    @PostMapping("/create")
    public ResponseEntity<UsersDto> create(@RequestBody UsersDto usersDto){
        UsersDto usersDto1 = service.create(usersDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersDto1);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The user exists",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsersDto.class))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user with this id")
    })
    @Operation(summary = "This road show user by id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<UsersDto> getById(@PathVariable Long id){
        UsersDto usersDto = service.findById(id);
        if(usersDto != null){
            return new ResponseEntity<>(usersDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The list of users is not empty",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UsersDto.class)))}),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user")
    })
    @Operation(summary = "This road shows all users")

    @GetMapping("/all")
    public ResponseEntity<List<UsersDto>> getAll(){
        List<UsersDto> usersDtoList = service.getAll();
        if(!usersDtoList.isEmpty()){
            return new ResponseEntity<>(usersDtoList, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The user was updated successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Failed to renew the parking space")
    })
    @Operation(summary = "This road updates user by id")
    @PutMapping("/update")
    public ResponseEntity<UsersDto> update(@RequestBody UsersDto usersDto) {
        UsersDto updatedUser = service.update(usersDto);
        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "The user was deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no any user with this id")
    })
    @Operation(summary = "This road deletes user by id")
    @DeleteMapping("/delete")
        public ResponseEntity<String> delete(@RequestParam Long id) {
        boolean deleted = service.deleteById(id);
        if (deleted) {
            return ResponseEntity.ok("Entity with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity with id " + id + " not found");
        }
    }
}
