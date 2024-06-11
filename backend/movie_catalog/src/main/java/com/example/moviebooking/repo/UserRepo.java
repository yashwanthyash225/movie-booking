package com.example.moviebooking.repo;

import com.example.moviebooking.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}
