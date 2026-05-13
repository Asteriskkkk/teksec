package com.example.moviemaster.service;

import com.example.moviemaster.entity.Film;
import com.example.moviemaster.exception.FilmNotFoundException;
import com.example.moviemaster.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    /**
     * Add a new film to the database
     */
    public Film addFilm(Film film) {
        return filmRepository.save(film);
    }

    /**
     * Get film details by film ID
     */
    public Film getFilmById(Long filmId) {
        Optional<Film> film = filmRepository.findById(filmId);
        if (!film.isPresent()) {
            throw new FilmNotFoundException(
                    "Film with ID " + filmId + " not found", filmId);
        }
        return film.get();
    }

    /**
     * Get films filtered by director and genre
     */
    public List<Film> getFilmsByDirectorAndGenre(String director, String genre) {
        return filmRepository.findByDirectorIgnoreCaseAndGenreIgnoreCase(director, genre);
    }

    /**
     * Get list of films produced by the given production house using its name (case-insensitive)
     */
    public List<Film> getFilmsByProductionHouseName(String houseName) {
        return filmRepository.findByProductionHouseNameIgnoreCase(houseName);
    }

    /**
     * Get all films
     */
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    /**
     * Remove a film from the database
     */
    public void removeFilm(Long filmId) {
        Film film = getFilmById(filmId);
        filmRepository.delete(film);
    }

    /**
     * Update film details
     */
    public Film updateFilm(Long filmId, Film filmDetails) {
        Film film = getFilmById(filmId);
        film.setTitle(filmDetails.getTitle());
        film.setDirector(filmDetails.getDirector());
        film.setGenre(filmDetails.getGenre());
        film.setBudget(filmDetails.getBudget());
        film.setReleaseDate(filmDetails.getReleaseDate());
        return filmRepository.save(film);
    }
}
