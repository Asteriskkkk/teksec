package com.example.goldratecalculation.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoldRateInfo {

    @Value("${gold.rate.18K}")
    private double rate18K;

    @Value("${gold.rate.22K}")
    private double rate22K;

    @Value("${gold.rate.24K}")
    private double rate24K;

    public double calculateTotalRate(String caratValue, double weightInGrams) {
        double ratePerGram = getRateForCarat(caratValue);
        return ratePerGram * weightInGrams;
    }

    private double getRateForCarat(String caratValue) {
        return switch (caratValue.trim().toUpperCase()) {
            case "18K" -> rate18K;
            case "22K" -> rate22K;
            case "24K" -> rate24K;
            default -> throw new IllegalArgumentException("Unsupported carat value: " + caratValue);
        };
    }
}
