package com.example.goldratecalculation.component;

import com.example.goldratecalculation.model.GoldRate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GoldRateInfo {

    private final Map<String, GoldRate> ratesByCarat;

    public GoldRateInfo(
            @Value("${gold.rate.18K}") double rate18K,
            @Value("${gold.rate.22K}") double rate22K,
            @Value("${gold.rate.24K}") double rate24K
    ) {
        this.ratesByCarat = Map.of(
                "18K", new GoldRate("18K", rate18K),
                "22K", new GoldRate("22K", rate22K),
                "24K", new GoldRate("24K", rate24K)
        );
    }

    public double calculateTotalRate(String caratValue, double weightInGrams) {
        GoldRate goldRate = getRateForCarat(caratValue);
        return goldRate.getRatePerGram() * weightInGrams;
    }

    private GoldRate getRateForCarat(String caratValue) {
        String normalizedCarat = caratValue.trim().toUpperCase();
        GoldRate goldRate = ratesByCarat.get(normalizedCarat);

        if (goldRate == null) {
            throw new IllegalArgumentException("Unsupported carat value: " + caratValue);
        }

        return goldRate;
    }
}
