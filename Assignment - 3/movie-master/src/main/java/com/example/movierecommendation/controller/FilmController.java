package com.example.movierecommendation.controller;

import com.example.movierecommendation.entity.Film;
import com.example.movierecommendation.service.FilmService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody Film film) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmService.addFilm(film));
    }

    @GetMapping
    public ResponseEntity<List<Film>> getFilmsByDirectorAndGenre(
            @RequestParam String director,
            @RequestParam String genre) {
        return ResponseEntity.ok(filmService.getFilmsByDirectorAndGenre(director, genre));
    }

    @GetMapping("/production-house/{productionHouseName}")
    public ResponseEntity<List<Film>> getFilmsByProductionHouseName(@PathVariable String productionHouseName) {
        return ResponseEntity.ok(filmService.getFilmsByProductionHouseName(productionHouseName));
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> removeFilm(@PathVariable Long filmId) {
        filmService.removeFilm(filmId);
        return ResponseEntity.noContent().build();
    }
}