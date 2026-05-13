package com.example.movierecommendation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChairmanUpdateRequest {

    @NotBlank
    @Size(min = 2, max = 100)
    private String chairmanName;

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }
}