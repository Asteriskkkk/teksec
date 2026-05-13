package com.example.moviemaster.repository;

import com.example.moviemaster.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    /**
     * Find a film by its ID
     */
    Optional<Film> findById(Long filmId);

    /**
     * Find films by director and genre
     */
    List<Film> findByDirectorIgnoreCaseAndGenreIgnoreCase(String director, String genre);

    /**
     * Find films by production house name (case-insensitive)
     */
    @Query("SELECT f FROM Film f WHERE LOWER(f.productionHouse.name) = LOWER(:houseName)")
    List<Film> findByProductionHouseNameIgnoreCase(@Param("houseName") String houseName);

    /**
     * Find all films by production house ID
     */
    List<Film> findByProductionHouseHouseId(Long houseId);
}
