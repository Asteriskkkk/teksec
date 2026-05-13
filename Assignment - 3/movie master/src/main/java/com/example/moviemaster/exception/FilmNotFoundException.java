package com.example.moviemaster.exception;

public class FilmNotFoundException extends RuntimeException {

    private Long filmId;

    public FilmNotFoundException(String message) {
        super(message);
    }

    public FilmNotFoundException(String message, Long filmId) {
        super(message);
        this.filmId = filmId;
    }

    public FilmNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }
}
