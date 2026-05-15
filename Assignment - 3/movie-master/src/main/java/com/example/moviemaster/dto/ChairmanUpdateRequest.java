package com.example.moviemaster.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ChairmanUpdateRequest {

    @NotNull(message = "Chairman name cannot be null")
    @NotEmpty(message = "Chairman name cannot be empty")
    @Size(min = 2, max = 100, message = "Chairman name must be between 2 and 100 characters")
    private String chairmanName;

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }

}
