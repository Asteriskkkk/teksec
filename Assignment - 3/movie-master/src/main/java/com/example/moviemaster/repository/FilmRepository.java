package com.example.moviemaster.repository;

import com.example.moviemaster.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {

    List<Film> findByDirectorIgnoreCaseAndGenreIgnoreCase(String director, String genre);

    List<Film> findByProductionHouse_ProductionHouseNameIgnoreCase(String productionHouseName);

}
