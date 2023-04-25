package com.api.models.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.api.models.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByUserLogin(String userLogin);
}
