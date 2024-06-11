package com.example.moviebooking.repo;

import com.example.moviebooking.model.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<ImageEntity, String> {
    ImageEntity findByMovieId(final Long movieId);
}
