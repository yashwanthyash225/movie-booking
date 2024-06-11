package com.example.moviebooking.service;

import com.example.moviebooking.model.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    ImageEntity getById(final String id);
    ImageEntity getByMovieId(final Long movieId);

    ImageEntity save(final Long movieId, final MultipartFile multipartFile) throws IOException;
}
