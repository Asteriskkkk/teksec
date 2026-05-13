package com.example.movierecommendation.service;

import com.example.movierecommendation.entity.Film;
import com.example.movierecommendation.entity.ProductionHouse;
import com.example.movierecommendation.exception.FilmNotFoundException;
import com.example.movierecommendation.exception.HouseNotFoundException;
import com.example.movierecommendation.repository.FilmRepository;
import com.example.movierecommendation.repository.ProductionHouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FilmService {

    private final FilmRepository filmRepository;
    private final ProductionHouseRepository productionHouseRepository;

    public FilmService(FilmRepository filmRepository, ProductionHouseRepository productionHouseRepository) {
        this.filmRepository = filmRepository;
        this.productionHouseRepository = productionHouseRepository;
    }

    public Film addFilm(Film film) {
        ProductionHouse requestedHouse = film.getProductionHouse();
        if (requestedHouse == null || requestedHouse.getHouseId() == null) {
            throw new IllegalArgumentException("productionHouse.houseId is required");
        }

        ProductionHouse productionHouse = productionHouseRepository.findById(requestedHouse.getHouseId())
                .orElseThrow(() -> new HouseNotFoundException(requestedHouse.getHouseId()));
        productionHouse.addFilm(film);
        return filmRepository.save(film);
    }

    @Transactional(readOnly = true)
    public List<Film> getFilmsByDirectorAndGenre(String director, String genre) {
        return filmRepository.findByDirectorIgnoreCaseAndGenreIgnoreCase(director, genre);
    }

    @Transactional(readOnly = true)
    public List<Film> getFilmsByProductionHouseName(String productionHouseName) {
        return filmRepository.findByProductionHouse_ProductionHouseNameIgnoreCase(productionHouseName);
    }

    public void removeFilm(Long filmId) {
        if (!filmRepository.existsById(filmId)) {
            throw new FilmNotFoundException(filmId);
        }
        filmRepository.deleteById(filmId);
    }
}