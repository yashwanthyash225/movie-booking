package com.example.moviebooking.controller;

import com.example.moviebooking.model.ImageEntity;
import com.example.moviebooking.service.impl.ImageServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/image")
@CrossOrigin(origins = {"*"})
public class ImageController {
    private final ImageServiceImpl imageService;

    public ImageController(ImageServiceImpl imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/byId")
    public ResponseEntity<String> byId(@RequestParam("id") final String id) {
        final ImageEntity imageEntity = imageService.getById(id);
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(imageEntity.getFirstImage().getData()));
    }

    @GetMapping("/byMovieId")
    public ResponseEntity<String> byMovieId(@RequestParam("movieId") final Long movieId) {
        final ImageEntity imageEntity = imageService.getByMovieId(movieId);
        return ResponseEntity.ok(Base64.getEncoder().encodeToString(imageEntity.getFirstImage().getData()));
    }

    @PostMapping("/addImage")
    public Long addPhoto(@RequestParam("movieId") final Long movieId,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        ImageEntity imageEntity = imageService.save(movieId, image);
        return imageEntity.getMovieId();
    }}
