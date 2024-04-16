package com.example.moviebooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TheatreMoviesResponseDto {

    @JsonProperty("localDate")
    private LocalDate localDate;

    @JsonProperty("week")
    private String week;

    @JsonProperty("month")
    private String month;

    @JsonProperty("date")
    private Integer date;

    @JsonProperty("movieDetails")
    private List<MovieDetailsDto> movieDetails;
}
