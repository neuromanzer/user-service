package com.neuro.userservice.repository;

import com.neuro.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsUserByEmail(String email);
}
