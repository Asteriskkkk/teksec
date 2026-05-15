package com.example.moviemaster.repository;

import com.example.moviemaster.entity.ProductionHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductionHouseRepository extends JpaRepository<ProductionHouse, Long> {

    Optional<ProductionHouse> findByProductionHouseNameIgnoreCase(String productionHouseName);

    @Query("SELECT ph FROM ProductionHouse ph WHERE (SELECT COUNT(f) FROM Film f WHERE f.productionHouse = ph) >= :minimumFilmCount")
    List<ProductionHouse> findProductionHousesWithMinimumFilmCount(@Param("minimumFilmCount") long minimumFilmCount);

}
