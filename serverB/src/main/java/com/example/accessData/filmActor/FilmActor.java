package com.example.accessData.filmActor;

import com.example.accessData.actor.Actor;
import com.example.accessData.film.Film;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "film_actor")
public class FilmActor {
    @JsonIgnore
    @EmbeddedId
    @Schema(example = "1", description = "")
    private FilmActorId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("filmId")
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @MapsId("actorId")
    @JoinColumn(name = "actor_id",nullable = false)
    private Actor actor;

    public FilmActorId getId() {
        return id;
    }

    public void setId(FilmActorId id) {
        this.id = id;
    }

    public String getFilm() {
        return film.getTitle();
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getActor() {
        return actor.getFirstName()+" "+actor.getLastName();
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}