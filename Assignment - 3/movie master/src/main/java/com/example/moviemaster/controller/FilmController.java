package com.example.moviemaster.controller;

import com.example.moviemaster.entity.Film;
import com.example.moviemaster.exception.FilmNotFoundException;
import com.example.moviemaster.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    /**
     * Add a new film
     * POST /api/films
     */
    @PostMapping
    public ResponseEntity<?> addFilm(@Valid @RequestBody Film film) {
        try {
            Film savedFilm = filmService.addFilm(film);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * Get film by ID
     * GET /api/films/{filmId}
     */
    @GetMapping("/{filmId}")
    public ResponseEntity<?> getFilmById(@PathVariable Long filmId) {
        try {
            Film film = filmService.getFilmById(filmId);
            return ResponseEntity.ok(film);
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Get all films
     * GET /api/films
     */
    @GetMapping
    public ResponseEntity<?> getAllFilms() {
        List<Film> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    /**
     * Get films by director and genre
     * GET /api/films/search?director={director}&genre={genre}
     */
    @GetMapping("/search")
    public ResponseEntity<?> getFilmsByDirectorAndGenre(
            @RequestParam String director,
            @RequestParam String genre) {
        try {
            List<Film> films = filmService.getFilmsByDirectorAndGenre(director, genre);
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Get films by production house name
     * GET /api/films/productionhouse/{houseName}
     */
    @GetMapping("/productionhouse/{houseName}")
    public ResponseEntity<?> getFilmsByProductionHouseName(@PathVariable String houseName) {
        try {
            List<Film> films = filmService.getFilmsByProductionHouseName(houseName);
            return ResponseEntity.ok(films);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Update film details
     * PUT /api/films/{filmId}
     */
    @PutMapping("/{filmId}")
    public ResponseEntity<?> updateFilm(
            @PathVariable Long filmId,
            @Valid @RequestBody Film filmDetails) {
        try {
            Film updatedFilm = filmService.updateFilm(filmId, filmDetails);
            return ResponseEntity.ok(updatedFilm);
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }

    /**
     * Remove a film from database
     * DELETE /api/films/{filmId}
     */
    @DeleteMapping("/{filmId}")
    public ResponseEntity<?> removeFilm(@PathVariable Long filmId) {
        try {
            filmService.removeFilm(filmId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: " + e.getMessage());
        }
    }
}
