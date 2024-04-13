package com.example.moviebooking.repo;

import com.example.moviebooking.model.ShowsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface ShowsRepo extends JpaRepository<ShowsEntity, Long> {
    List<ShowsEntity> findAllByScreenId(final Long screenId);
    List<ShowsEntity> findAllByMovieId(final Long movieId);
    List<ShowsEntity> findAllByScreenIdAndMovieId(final Long screenId, final Long movieId);

    List<ShowsEntity> findAllByMovieIdAndValid(final Long movieId, final Boolean valid);

    List<ShowsEntity> findAllByMovieIdAndValidAndTimingGreaterThanEqual(final Long movieId,
                                                                        final Boolean valid,
                                                                        final LocalDateTime timing);

    List<ShowsEntity> findAllByScreenIdInAndValid(final Collection<Long> screenIds, final Boolean valid);
}
