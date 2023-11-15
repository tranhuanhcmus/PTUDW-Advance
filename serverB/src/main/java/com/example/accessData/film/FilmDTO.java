package com.example.accessData.film;

import com.example.accessData.filmActor.FilmActor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Film}
 */
public class FilmDTO implements Serializable {
    @Schema(example = "2")
    private final Short id;

    @Schema(example = "StarWar")
    @Size(message = "Max Size of Title is 255", max = 255)
    private final String title;

    @Schema(example = "Description of movie")
    private final String description;

    @Schema(example = "2017")
    private final Integer releaseYear;

    @Schema(example = "65")
    private final Short rentalDuration;

    @Schema(example = "6.5")
    private final BigDecimal rentalRate;

    @Schema(example = "114")
    private final Short length;

    @Schema(example = "6.99")
    private final BigDecimal replacementCost;

    @Schema(example = "P")
    private final String rating;

    @Schema(example = "Trailers,Deleted Scenes")
    private final String specialFeatures;

    @Schema(example = "Vietnamese")
    private final String language;
    @JsonIgnore
    private final Byte languageId;

    private final Set<FilmActor> filmActors;


    public FilmDTO(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getReleaseYear();
        this.rentalDuration = film.getRentalDuration().shortValue();
        this.rentalRate = film.getRentalRate();
        this.length = film.getLength();
        this.replacementCost = film.getReplacementCost();
        this.rating = film.getRating();
        this.specialFeatures = film.getSpecialFeatures();
        this.language = film.getLanguage().getName();
        this.languageId = film.getLanguage().getId();
        this.filmActors = film.getFilmActors();
    }

    public Short getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public Short getLength() {
        return length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmDTO entity = (FilmDTO) o;
        return Objects.equals(this.title, entity.title) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.releaseYear, entity.releaseYear) &&
                Objects.equals(this.rentalDuration, entity.rentalDuration) &&
                Objects.equals(this.rentalRate, entity.rentalRate) &&
                Objects.equals(this.length, entity.length) &&
                Objects.equals(this.replacementCost, entity.replacementCost) &&
                Objects.equals(this.rating, entity.rating) &&
                Objects.equals(this.specialFeatures, entity.specialFeatures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, releaseYear, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "title = " + title + ", " +
                "description = " + description + ", " +
                "releaseYear = " + releaseYear + ", " +
                "rentalDuration = " + rentalDuration + ", " +
                "rentalRate = " + rentalRate + ", " +
                "length = " + length + ", " +
                "replacementCost = " + replacementCost + ", " +
                "rating = " + rating + ", " +
                "specialFeatures = " + specialFeatures + ")";
    }

    public String getLanguage() {
        return language;
    }

    public Byte getLanguageId() {
        return languageId;
    }

    @JsonProperty("actors")
    public Set<String> getFilmActors() {
        return filmActors.stream()
                .map(FilmActor::getActor)
                .collect(Collectors.toSet());
    }

}