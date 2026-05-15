package com.example.moviemaster.service;

import com.example.moviemaster.dto.ChairmanUpdateRequest;
import com.example.moviemaster.entity.ProductionHouse;
import com.example.moviemaster.exception.ProductionHouseNotFoundException;
import com.example.moviemaster.repository.ProductionHouseRepository;
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
        ProductionHouse productionHouse = getProductionHouseById(houseId);
        productionHouse.setChairmanName(request.getChairmanName());
        return productionHouseRepository.save(productionHouse);
    }

    public ProductionHouse getProductionHouseById(Long houseId) {
        return productionHouseRepository.findById(houseId)
                .orElseThrow(() -> new ProductionHouseNotFoundException(
                        "Production house not found with houseId: " + houseId));
    }

    public List<ProductionHouse> getProductionHousesByMinimumFilmCount(long minimumFilmCount) {
        return productionHouseRepository.findProductionHousesWithMinimumFilmCount(minimumFilmCount);
    }

}
