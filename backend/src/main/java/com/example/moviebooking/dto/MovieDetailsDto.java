package com.example.moviebooking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MovieDetailsDto {
    @JsonProperty("movie")
    private MovieDto movieDto;

    @JsonProperty("showTimings")
    private List<ShowTimingsDto> showTimingsDtos;
}
