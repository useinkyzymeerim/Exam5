package com.jdbc.exam5.service.impl;
import com.jdbc.exam5.dto.ParkingPlaceDto;
import com.jdbc.exam5.dto.UsersDto;
import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.entity.Users;
import com.jdbc.exam5.repo.UsersRepo;
import com.jdbc.exam5.service.UsersService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    private final UsersRepo usersRepo;
    @Override
    public List<UsersDto> getAll() {
        List<Users> usersDtoList = usersRepo.findAll();

        List<UsersDto> usersDtos = new ArrayList<>();

        for (Users users : usersDtoList) {

            UsersDto usersDto = UsersDto.builder()
                    .id(users.getId())
                    .name(users.getName())
                    .sureName(users.getSureName())
                    .build();
            usersDtos.add(usersDto);
        }
        return usersDtos;
    }


    @Override
    public UsersDto findById(Long id) {
        Users users = usersRepo.findById(id).orElseThrow(() -> new EntityNotFoundException(""));

        return UsersDto.builder()
                .id(users.getId())
                .name(users.getName())
                .sureName(users.getSureName())
                .build();
    }


    @Override
    public UsersDto create(UsersDto usersDto) {

        Users users = Users.builder()
                .name(usersDto.getName())
                .sureName(usersDto.getSureName())

                .build();

        try {
            usersRepo.save(users);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return usersDto;
    }


    @Override
    public boolean deleteById(Long id) {
        if (usersRepo.existsById(id)) {
            usersRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UsersDto update(UsersDto usersDto) {
        Users user = usersRepo.findById(usersDto.getId())
                .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + usersDto.getId()));
        if (usersDto.getName() != null){
            user.setName(usersDto.getName());
        }
        if (usersDto.getSureName() != null){
            user.setSureName(usersDto.getSureName());
        }

        usersRepo.save(user);
        return usersDto;
    }



}
