package com.example.accessData.actor;

import com.example.accessData.filmActor.FilmActor;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link Actor}
 */
public class ActorDTO implements Serializable {

    @Schema(example = "21", description = "")
    private final Short id;

    @Schema(example = "Nguyen", description = "")
    @NotEmpty(message = "Thiếu firstName")
    private final String firstName;

    @Schema(example = "Tan", description = "")
    @NotEmpty(message = "Thiếu lastName")
    private final String lastName;
    private final Set<FilmActor> filmActors;


    public ActorDTO(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.filmActors = actor.getFilmActors();
    }

    public Short getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActorDTO entity = (ActorDTO) o;
        return Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.lastName, entity.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ")";
    }

    @JsonProperty("films")
    public Set<String> getFilmActors() {
        return filmActors.stream()
                .map(FilmActor::getFilm)
                .collect(Collectors.toSet());
    }
}