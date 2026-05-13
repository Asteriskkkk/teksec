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

    /**
     * Find a production house by its ID
     */
    Optional<ProductionHouse> findById(Long houseId);

    /**
     * Find production houses with film count greater than or equal to the given value
     */
    @Query("SELECT ph FROM ProductionHouse ph WHERE (SELECT COUNT(f) FROM Film f WHERE f.productionHouse.houseId = ph.houseId) >= :filmCount")
    List<ProductionHouse> findByFilmCountGreaterThanOrEqual(@Param("filmCount") int filmCount);

    /**
     * Find a production house by name (case-insensitive)
     */
    Optional<ProductionHouse> findByNameIgnoreCase(String name);
}
