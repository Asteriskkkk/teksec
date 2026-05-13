package com.example.moviemaster.service;

import com.example.moviemaster.entity.ProductionHouse;
import com.example.moviemaster.exception.ProductionHouseNotFoundException;
import com.example.moviemaster.repository.ProductionHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductionHouseService {

    @Autowired
    private ProductionHouseRepository productionHouseRepository;

    /**
     * Add a new production house to the database
     */
    public ProductionHouse addProductionHouse(ProductionHouse productionHouse) {
        return productionHouseRepository.save(productionHouse);
    }

    /**
     * Get production house details by house ID
     */
    public ProductionHouse getProductionHouseById(Long houseId) {
        Optional<ProductionHouse> productionHouse = productionHouseRepository.findById(houseId);
        if (!productionHouse.isPresent()) {
            throw new ProductionHouseNotFoundException(
                    "Production house with ID " + houseId + " not found", houseId);
        }
        return productionHouse.get();
    }

    /**
     * Update the chairman's name for a given production house ID
     */
    public ProductionHouse updateChairmanName(Long houseId, String chairmanName) {
        ProductionHouse productionHouse = getProductionHouseById(houseId);
        productionHouse.setChairmanName(chairmanName);
        return productionHouseRepository.save(productionHouse);
    }

    /**
     * Get list of production houses having number of films >= given value
     */
    public List<ProductionHouse> getProductionHousesByFilmCount(int filmCount) {
        return productionHouseRepository.findByFilmCountGreaterThanOrEqual(filmCount);
    }

    /**
     * Get all production houses
     */
    public List<ProductionHouse> getAllProductionHouses() {
        return productionHouseRepository.findAll();
    }

    /**
     * Delete a production house by ID
     */
    public void deleteProductionHouse(Long houseId) {
        ProductionHouse productionHouse = getProductionHouseById(houseId);
        productionHouseRepository.delete(productionHouse);
    }
}
