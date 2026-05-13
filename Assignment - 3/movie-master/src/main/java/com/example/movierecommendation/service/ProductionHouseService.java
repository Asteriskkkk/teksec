package com.example.movierecommendation.service;

import com.example.movierecommendation.dto.ChairmanUpdateRequest;
import com.example.movierecommendation.entity.ProductionHouse;
import com.example.movierecommendation.exception.HouseNotFoundException;
import com.example.movierecommendation.repository.ProductionHouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductionHouseService {

    private final ProductionHouseRepository productionHouseRepository;

    public ProductionHouseService(ProductionHouseRepository productionHouseRepository) {
        this.productionHouseRepository = productionHouseRepository;
    }

    public ProductionHouse addProductionHouse(ProductionHouse productionHouse) {
        return productionHouseRepository.save(productionHouse);
    }

    public ProductionHouse updateChairmanName(Long houseId, ChairmanUpdateRequest request) {
        ProductionHouse productionHouse = productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(houseId));
        productionHouse.setChairmanName(request.getChairmanName());
        return productionHouseRepository.save(productionHouse);
    }

    @Transactional(readOnly = true)
    public ProductionHouse getProductionHouseById(Long houseId) {
        return productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new HouseNotFoundException(houseId));
    }

    @Transactional(readOnly = true)
    public List<ProductionHouse> getProductionHousesWithMinimumFilms(long minimumFilmCount) {
        return productionHouseRepository.findByMinimumFilmCount(minimumFilmCount);
    }
}