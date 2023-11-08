package com.example.accessData.film;

import com.example.accessData.filmActor.FilmActor;
import com.example.accessData.language.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "film")
public class Film {

    static final List<String> ERatings = Arrays.asList("NC-17", "R", "G", "PG-13", "PG");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", nullable = false)
    private Short id;

    @Size(max = 255)
    @NotNull(message = "Title is not null")
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @NotNull(message = "Duration is not null")
    @Column(name = "rental_duration", nullable = false)
    private Byte rentalDuration;

    @NotNull(message = "rentalRate is not null")
    @Column(name = "rental_rate", nullable = false, precision = 4, scale = 2)
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @NotNull(message = "replacementCost is not Null")
    @Column(name = "replacement_cost", nullable = false, precision = 5, scale = 2)
    private BigDecimal replacementCost;

    @Lob
    @Column(name = "rating")
    private String rating;

    @Lob
    @Column(name = "special_features")
    private String specialFeatures;
    @ManyToOne(fetch = FetchType.LAZY, optional = false,targetEntity = Language.class)
    @JoinColumn(name = "language_id", nullable = false)
    @NotNull(message = "Language is not null")
    private Language language;

    public Film(){

    }

    @OneToMany(mappedBy = "film")
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setFromFilmDTO(FilmDTO filmDTO) {
        this.title = filmDTO.getTitle();
        this.description = filmDTO.getDescription();
        this.releaseYear = filmDTO.getReleaseYear();
        this.rentalDuration = filmDTO.getRentalDuration();
        this.rentalRate = filmDTO.getRentalRate();
        this.length = filmDTO.getLength();
        this.replacementCost = filmDTO.getReplacementCost();
        this.rating = filmDTO.getRating();
        this.specialFeatures = filmDTO.getSpecialFeatures();
    }


    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Byte getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Byte rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Set<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Set<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }



}