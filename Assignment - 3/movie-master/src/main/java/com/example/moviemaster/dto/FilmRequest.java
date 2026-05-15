package com.example.moviemaster.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

import java.time.LocalDate;

public class FilmRequest {

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
    private String title;

    @NotNull(message = "Director cannot be null")
    @NotEmpty(message = "Director cannot be empty")
    @Size(min = 2, max = 100, message = "Director must be between 2 and 100 characters")
    private String director;

    @NotNull(message = "Genre cannot be null")
    @NotEmpty(message = "Genre cannot be empty")
    @Size(min = 2, max = 50, message = "Genre must be between 2 and 50 characters")
    private String genre;

    @NotNull(message = "Budget cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Budget must be greater than 0")
    private Double budget;

    @NotNull(message = "Release date cannot be null")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotNull(message = "Production house cannot be null")
    @Valid
    private ProductionHouseReference productionHouse;

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

    public ProductionHouseReference getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(ProductionHouseReference productionHouse) {
        this.productionHouse = productionHouse;
    }

}
