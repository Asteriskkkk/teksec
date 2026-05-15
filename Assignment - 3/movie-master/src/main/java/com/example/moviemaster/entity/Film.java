package com.example.moviemaster.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "films")
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    @Column(nullable = false, length = 150)
    private String title;

    @NotNull(message = "Director cannot be null")
    @NotEmpty(message = "Director cannot be empty")
    @Size(min = 2, max = 100, message = "Director must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String director;

    @NotNull(message = "Genre cannot be null")
    @NotEmpty(message = "Genre cannot be empty")
    @Size(min = 2, max = 50, message = "Genre must be between 2 and 50 characters")
    @Column(nullable = false, length = 50)
    private String genre;

    @NotNull(message = "Budget cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    @Column(nullable = false)
    private Double budget;

    @NotNull(message = "Release date cannot be null")
    @PastOrPresent(message = "Release date cannot be in the future")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @NotNull(message = "Production house cannot be null")
    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private ProductionHouse productionHouse;

    public Long getFilmId() {
        return filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ProductionHouse getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(ProductionHouse productionHouse) {
        this.productionHouse = productionHouse;
    }

}
