package com.example.moviemaster.controller;

import com.example.moviemaster.dto.FilmRequest;
import com.example.moviemaster.entity.Film;
import com.example.moviemaster.service.FilmService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/films")
@Validated
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    public ResponseEntity<Film> addFilm(@Valid @RequestBody FilmRequest request) {
        Film createdFilm = filmService.addFilm(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    @GetMapping
    public ResponseEntity<List<Film>> getFilmsByDirectorAndGenre(
            @RequestParam @NotBlank String director,
            @RequestParam @NotBlank String genre) {
        List<Film> films = filmService.getFilmsByDirectorAndGenre(director, genre);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/production-house/{name}")
    public ResponseEntity<List<Film>> getFilmsByProductionHouseName(@PathVariable String name) {
        List<Film> films = filmService.getFilmsByProductionHouseName(name);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/{filmId}")
    public ResponseEntity<Void> removeFilm(@PathVariable Long filmId) {
        filmService.removeFilm(filmId);
        return ResponseEntity.noContent().build();
    }

}
