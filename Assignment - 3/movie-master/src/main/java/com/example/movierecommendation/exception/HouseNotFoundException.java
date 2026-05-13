package com.example.movierecommendation.exception;

public class HouseNotFoundException extends RuntimeException {

    public HouseNotFoundException(Long houseId) {
        super("Production house not found for houseId: " + houseId);
    }
}