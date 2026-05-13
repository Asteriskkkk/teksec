package com.example.movierecommendation.repository;

import com.example.movierecommendation.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByDirectorIgnoreCaseAndGenreIgnoreCase(String director, String genre);

    List<Film> findByProductionHouse_ProductionHouseNameIgnoreCase(String productionHouseName);
}