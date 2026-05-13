package com.example.movierecommendation.repository;

import com.example.movierecommendation.entity.ProductionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductionHouseRepository extends JpaRepository<ProductionHouse, Long> {

    Optional<ProductionHouse> findByProductionHouseNameIgnoreCase(String productionHouseName);

    @Query("select ph from ProductionHouse ph left join ph.films f group by ph having count(f) >= :minimumFilmCount")
    List<ProductionHouse> findByMinimumFilmCount(@Param("minimumFilmCount") long minimumFilmCount);
}