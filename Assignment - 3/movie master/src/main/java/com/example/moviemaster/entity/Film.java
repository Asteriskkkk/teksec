package com.example.moviemaster.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long filmId;

    @NotEmpty(message = "Film title cannot be empty")
    @Column(nullable = false, length = 150)
    private String title;

    @NotEmpty(message = "Director name cannot be empty")
    @Column(nullable = false, length = 100)
    private String director;

    @NotEmpty(message = "Genre cannot be empty")
    @Column(nullable = false, length = 100)
    private String genre;

    @Positive(message = "Budget must be a positive value")
    @Column(nullable = false)
    private Double budget;

    @PastOrPresent(message = "Release date cannot be in the future")
    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id", nullable = false)
    private ProductionHouse productionHouse;

    // Constructors
    public Film() {
    }

    public Film(String title, String director, String genre, Double budget, LocalDate releaseDate, ProductionHouse productionHouse) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.budget = budget;
        this.releaseDate = releaseDate;
        this.productionHouse = productionHouse;
    }

    // Getters and Setters
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

    @Override
    public String toString() {
        return "Film{" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", budget=" + budget +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
