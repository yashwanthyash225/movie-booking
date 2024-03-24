package com.example.moviebooking.repo;

import com.example.moviebooking.model.ShowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowsRepo extends JpaRepository<ShowsEntity, Long> {
    List<ShowsEntity> findAllByScreenId(final Long screenId);
    List<ShowsEntity> findAllByMovieId(final Long movieId);
    List<ShowsEntity> findAllByScreenIdAndMovieId(final Long screenId, final Long movieId);

}
