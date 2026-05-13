package com.example.movierecommendation.controller;

import com.example.movierecommendation.dto.ChairmanUpdateRequest;
import com.example.movierecommendation.entity.ProductionHouse;
import com.example.movierecommendation.service.ProductionHouseService;
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
@RequestMapping("/api/production-houses")
@Validated
public class ProductionHouseController {

    private final ProductionHouseService productionHouseService;

    public ProductionHouseController(ProductionHouseService productionHouseService) {
        this.productionHouseService = productionHouseService;
    }

    @PostMapping
    public ResponseEntity<ProductionHouse> addProductionHouse(@Valid @RequestBody ProductionHouse productionHouse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productionHouseService.addProductionHouse(productionHouse));
    }

    @PutMapping("/{houseId}/chairman")
    public ResponseEntity<ProductionHouse> updateChairmanName(
            @PathVariable Long houseId,
            @Valid @RequestBody ChairmanUpdateRequest request) {
        return ResponseEntity.ok(productionHouseService.updateChairmanName(houseId, request));
    }

    @GetMapping("/{houseId}")
    public ResponseEntity<ProductionHouse> getProductionHouseById(@PathVariable Long houseId) {
        return ResponseEntity.ok(productionHouseService.getProductionHouseById(houseId));
    }

    @GetMapping
    public ResponseEntity<List<ProductionHouse>> getProductionHousesByMinimumFilmCount(
            @RequestParam @Min(0) long minimumFilmCount) {
        return ResponseEntity.ok(productionHouseService.getProductionHousesWithMinimumFilms(minimumFilmCount));
    }
}