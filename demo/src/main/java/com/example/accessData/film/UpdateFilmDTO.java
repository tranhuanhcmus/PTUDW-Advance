package com.example.accessData.film;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class UpdateFilmDTO {
    public Short getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Short languageId) {
        this.languageId = languageId;
    }

    @Schema(example = "StarWar", description = "Hollywood")
    @Size(max = 255)
    private String title;

    @Schema(example = "Action movie", description = "Description of film")
    private String description;

    @Schema(example = "2017", description = "Release year of the movie")
    @Min(value = 1900, message =  "Release year must be greater than 1900")
    @Max(value = 2300, message =  "Release year must be smaller than 2300")
    private Integer releaseYear;

    @Schema(example = "65", description = "Rental duration, not null")
    private Short rentalDuration;

    @Schema(example = "5.71", description = "Rental rate, not null")
    private BigDecimal rentalRate;

    @Schema(example = "117", description = "Duration of the movie (minutes)")
    private Short length;

    @Schema(example = "21.66", description = "Cost of replacement (dollar)")
    private BigDecimal replacementCost;

    @Schema(example = "R", description = "Rating")
    private String rating;

    @Schema(example = "Trailers,Deleted Scenes", description = "Set of special feautures")
    private String specialFeatures;

    @Schema(example = "1", description = "Language Id")
    @NotNull(message = "Language is not null")
    private Short languageId;

    public UpdateFilmDTO(){

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

    public Short getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
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
}
