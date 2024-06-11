package com.example.moviebooking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("theatreId")
    private Long theatreId;

    @JsonProperty("capacity")
    private Integer capacity;

    @JsonProperty("name")
    private String name;
}
