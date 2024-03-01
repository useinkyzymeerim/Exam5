package com.jdbc.exam5.repo;

import com.jdbc.exam5.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
}
