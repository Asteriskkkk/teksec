package com.example.moviemaster.dto;

import jakarta.validation.constraints.NotNull;

public class ProductionHouseReference {

    @NotNull(message = "Production house id cannot be null")
    private Long houseId;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

}
