package com.example.moviemaster.exception;

public class ProductionHouseNotFoundException extends RuntimeException {

    private Long houseId;

    public ProductionHouseNotFoundException(String message) {
        super(message);
    }

    public ProductionHouseNotFoundException(String message, Long houseId) {
        super(message);
        this.houseId = houseId;
    }

    public ProductionHouseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }
}
