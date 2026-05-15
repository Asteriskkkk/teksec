package com.example.moviemaster.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "production_houses")
@JsonIgnoreProperties({"films"})
public class ProductionHouse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;

    @NotNull(message = "Production house name cannot be null")
    @NotEmpty(message = "Production house name cannot be empty")
    @Size(min = 2, max = 100, message = "Production house name must be between 2 and 100 characters")
    @Column(nullable = false, unique = true, length = 100)
    private String productionHouseName;

    @NotNull(message = "Established year cannot be null")
    @Min(value = 1800, message = "Established year must be at least 1800")
    @Max(value = 2100, message = "Established year must be a valid year")
    @Column(nullable = false)
    private Integer establishedYear;

    @NotNull(message = "Chairman name cannot be null")
    @NotEmpty(message = "Chairman name cannot be empty")
    @Size(min = 2, max = 100, message = "Chairman name must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String chairmanName;

    @NotNull(message = "Country cannot be null")
    @NotEmpty(message = "Country cannot be empty")
    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    @Column(nullable = false, length = 100)
    private String country;

    @OneToMany(mappedBy = "productionHouse")
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

}
