package com.example.moviebooking.repo;

import com.example.moviebooking.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<MovieEntity, Long> {
    MovieEntity findByTitle(final String title);
}
