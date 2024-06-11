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
public class SeatDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("showsId")
    private Long showsId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("available")
    private Boolean available;

    @JsonProperty("blocked_by")
    private Long blockedBy;
}
