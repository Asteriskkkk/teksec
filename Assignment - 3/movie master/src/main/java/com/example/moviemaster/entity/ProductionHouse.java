package com.example.moviemaster.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "production_house")
public class ProductionHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @NotEmpty(message = "Production house name cannot be empty")
    @Column(nullable = false, length = 100)
    private String name;

    @Min(value = 1800, message = "Established year must be greater than or equal to 1800")
    @Max(value = 2024, message = "Established year must be less than or equal to current year")
    @Column(nullable = false)
    private Integer establishedYear;

    @NotEmpty(message = "Chairman name cannot be empty")
    @Column(nullable = false, length = 100)
    private String chairmanName;

    @NotEmpty(message = "Country cannot be empty")
    @Column(nullable = false, length = 100)
    private String country;

    @OneToMany(mappedBy = "productionHouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Film> films = new ArrayList<>();

    // Constructors
    public ProductionHouse() {
    }

    public ProductionHouse(String name, Integer establishedYear, String chairmanName, String country) {
        this.name = name;
        this.establishedYear = establishedYear;
        this.chairmanName = chairmanName;
        this.country = country;
    }

    // Getters and Setters
    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ProductionHouse{" +
                "houseId=" + houseId +
                ", name='" + name + '\'' +
                ", establishedYear=" + establishedYear +
                ", chairmanName='" + chairmanName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
