package com.example.goldratecalculation.model;

public class GoldRate {

    private final String caratValue;
    private final double ratePerGram;

    public GoldRate(String caratValue, double ratePerGram) {
        this.caratValue = caratValue;
        this.ratePerGram = ratePerGram;
    }

    public String getCaratValue() {
        return caratValue;
    }

    public double getRatePerGram() {
        return ratePerGram;
    }
}
