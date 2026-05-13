package com.example.moviemaster.controller;

import com.example.moviemaster.entity.ProductionHouse;
import com.example.moviemaster.exception.ProductionHouseNotFoundException;
import com.example.moviemaster.service.ProductionHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productionhouses")
public class ProductionHouseController {

    @Autowired
    private ProductionHouseService productionHouseService;

    /**
     * Add a new production house
     * POST /api/productionhouses
     */
    @PostMapping
    public ResponseEntity<?> addProductionHouse(@Valid @RequestBody ProductionHouse productionHouse) {
        try {
            ProductionHouse savedHouse = productionHouseService.addProductionHouse(productionHouse);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedHouse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * Get production house by ID
     * GET /api/productionhouses/{houseId}
     */
    @GetMapping("/{houseId}")
    public ResponseEntity<?> getProductionHouseById(@PathVariable Long houseId) {
        try {
            ProductionHouse productionHouse = productionHouseService.getProductionHouseById(houseId);
            return ResponseEntity.ok(productionHouse);
        } catch (ProductionHouseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Get all production houses
     * GET /api/productionhouses
     */
    @GetMapping
    public ResponseEntity<?> getAllProductionHouses() {
        List<ProductionHouse> productionHouses = productionHouseService.getAllProductionHouses();
        return ResponseEntity.ok(productionHouses);
    }

    /**
     * Update chairman's name for a production house
     * PUT /api/productionhouses/{houseId}/chairman
     */
    @PutMapping("/{houseId}/chairman")
    public ResponseEntity<?> updateChairmanName(
            @PathVariable Long houseId,
            @RequestParam String chairmanName) {
        try {
            ProductionHouse updatedHouse = productionHouseService.updateChairmanName(houseId, chairmanName);
            return ResponseEntity.ok(updatedHouse);
        } catch (ProductionHouseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Get production houses with film count >= given value
     * GET /api/productionhouses/byfilmcount/{filmCount}
     */
    @GetMapping("/byfilmcount/{filmCount}")
    public ResponseEntity<?> getProductionHousesByFilmCount(@PathVariable int filmCount) {
        try {
            List<ProductionHouse> productionHouses = productionHouseService.getProductionHousesByFilmCount(filmCount);
            return ResponseEntity.ok(productionHouses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Delete a production house by ID
     * DELETE /api/productionhouses/{houseId}
     */
    @DeleteMapping("/{houseId}")
    public ResponseEntity<?> deleteProductionHouse(@PathVariable Long houseId) {
        try {
            productionHouseService.deleteProductionHouse(houseId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ProductionHouseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}
