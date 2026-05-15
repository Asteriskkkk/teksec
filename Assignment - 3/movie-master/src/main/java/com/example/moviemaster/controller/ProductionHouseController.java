package com.example.moviemaster.controller;

import com.example.moviemaster.dto.ChairmanUpdateRequest;
import com.example.moviemaster.entity.ProductionHouse;
import com.example.moviemaster.service.ProductionHouseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/production-houses")
@Validated
public class ProductionHouseController {

    private final ProductionHouseService productionHouseService;

    public ProductionHouseController(ProductionHouseService productionHouseService) {
        this.productionHouseService = productionHouseService;
    }

    @PostMapping
    public ResponseEntity<ProductionHouse> addProductionHouse(@Valid @RequestBody ProductionHouse productionHouse) {
        ProductionHouse createdProductionHouse = productionHouseService.addProductionHouse(productionHouse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductionHouse);
    }

    @PutMapping("/{houseId}/chairman")
    public ResponseEntity<ProductionHouse> updateChairmanName(
            @PathVariable Long houseId,
            @Valid @RequestBody ChairmanUpdateRequest request) {
        ProductionHouse updatedProductionHouse = productionHouseService.updateChairmanName(houseId, request);
        return ResponseEntity.ok(updatedProductionHouse);
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ProductionHouse> getProductionHouseById(@PathVariable Long houseId) {
        ProductionHouse productionHouse = productionHouseService.getProductionHouseById(houseId);
        return ResponseEntity.ok(productionHouse);
    }

    @GetMapping
    public ResponseEntity<List<ProductionHouse>> getProductionHousesByMinimumFilmCount(
            @RequestParam @Min(value = 0, message = "Minimum film count must be zero or greater") long minimumFilmCount) {
        List<ProductionHouse> productionHouses = productionHouseService.getProductionHousesByMinimumFilmCount(minimumFilmCount);
        return ResponseEntity.ok(productionHouses);
    }

}
