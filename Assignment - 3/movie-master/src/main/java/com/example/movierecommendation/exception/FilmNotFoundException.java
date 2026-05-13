package com.example.movierecommendation.exception;

public class FilmNotFoundException extends RuntimeException {

    public FilmNotFoundException(Long filmId) {
        super("Film not found for filmId: " + filmId);
    }
}