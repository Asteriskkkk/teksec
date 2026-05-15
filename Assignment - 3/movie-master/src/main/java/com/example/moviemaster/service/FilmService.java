package com.example.moviemaster.service;

import com.example.moviemaster.dto.FilmRequest;
import com.example.moviemaster.entity.Film;
import com.example.moviemaster.entity.ProductionHouse;
import com.example.moviemaster.exception.FilmNotFoundException;
import com.example.moviemaster.exception.ProductionHouseNotFoundException;
import com.example.moviemaster.repository.FilmRepository;
import com.example.moviemaster.repository.ProductionHouseRepository;
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

    public Film addFilm(FilmRequest request) {
        ProductionHouse productionHouse = productionHouseRepository.findById(request.getProductionHouse().getHouseId())
                .orElseThrow(() -> new ProductionHouseNotFoundException(
                "Production house not found with houseId: " + request.getProductionHouse().getHouseId()));

        Film film = new Film();
        film.setTitle(request.getTitle());
        film.setDirector(request.getDirector());
        film.setGenre(request.getGenre());
        film.setBudget(request.getBudget());
        film.setReleaseDate(request.getReleaseDate());
        film.setProductionHouse(productionHouse);

        return filmRepository.save(film);
    }

    public List<Film> getFilmsByDirectorAndGenre(String director, String genre) {
        return filmRepository.findByDirectorIgnoreCaseAndGenreIgnoreCase(director, genre);
    }

    public List<Film> getFilmsByProductionHouseName(String productionHouseName) {
        return filmRepository.findByProductionHouse_ProductionHouseNameIgnoreCase(productionHouseName);
    }

    public void removeFilm(Long filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with filmId: " + filmId));
        filmRepository.delete(film);
    }

}
