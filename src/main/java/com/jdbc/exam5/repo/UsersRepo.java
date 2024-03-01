package com.jdbc.exam5.repo;

import com.jdbc.exam5.entity.ParkingPlace;
import com.jdbc.exam5.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepo extends JpaRepository<Users, Long> {

}
