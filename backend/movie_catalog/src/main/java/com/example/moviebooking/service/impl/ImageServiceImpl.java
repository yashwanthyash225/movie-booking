package com.example.moviebooking.service.impl;

import com.example.moviebooking.model.ImageEntity;
import com.example.moviebooking.repo.ImageRepository;
import com.example.moviebooking.service.ImageService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public ImageEntity getById(final String id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public ImageEntity getByMovieId(final Long movieId) {
        return imageRepository.findByMovieId(movieId);
    }

    @Override
    public ImageEntity save(final Long movieId, final MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile.getSize());
        ImageEntity imageEntity = imageRepository.save(ImageEntity.builder()
                .id("2")
                .movieId(1L)
                .firstImage(new Binary(BsonBinarySubType.BINARY, multipartFile.getBytes()))
                .build());
        System.out.println("--------------" + imageEntity.getMovieId() + " " + imageEntity.getFirstImage().getData());
        return imageEntity;
    }
}
