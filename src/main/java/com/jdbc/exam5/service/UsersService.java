package com.jdbc.exam5.service;

import com.jdbc.exam5.dto.UsersDto;

import java.util.List;

public interface UsersService {
    List<UsersDto> getAll();

    UsersDto findById(Long id);

    UsersDto create (UsersDto usersDto);

    boolean deleteById(Long id);
    UsersDto update(UsersDto usersDto) ;
}
