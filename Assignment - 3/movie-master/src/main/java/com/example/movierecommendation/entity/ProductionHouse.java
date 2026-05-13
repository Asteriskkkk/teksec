package com.example.movierecommendation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "production_houses")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "films"})
public class ProductionHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String productionHouseName;

    @NotNull
    @Min(1800)
    @Max(2100)
    private Integer establishedYear;

    @NotBlank
    @Size(min = 2, max = 100)
    private String chairmanName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String country;

    @OneToMany(mappedBy = "productionHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> films = new ArrayList<>();

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getProductionHouseName() {
        return productionHouseName;
    }

    public void setProductionHouseName(String productionHouseName) {
        this.productionHouseName = productionHouseName;
    }

    public Integer getEstablishedYear() {
        return establishedYear;
    }

    public void setEstablishedYear(Integer establishedYear) {
        this.establishedYear = establishedYear;
    }

    public String getChairmanName() {
        return chairmanName;
    }

    public void setChairmanName(String chairmanName) {
        this.chairmanName = chairmanName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        films.add(film);
        film.setProductionHouse(this);
    }

    public void removeFilm(Film film) {
        films.remove(film);
        film.setProductionHouse(null);
    }
}