package com.example.accessData.language;

import com.example.accessData.film.Film;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "language", schema = "sakila")
public class Language {
    @Id
    @Column(name = "language_id", nullable = false)
    private Byte id;

    @Size(max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToMany(mappedBy = "language",targetEntity = Film.class)
    private Set<Film> films = new LinkedHashSet<>();

    public Language(Byte id, String name, Set<Film> films) {
        this.id = id;
        this.name = name;
        this.films = films;
    }

    public Language(Language language) {
        this.id = language.getId();
        this.name = language.getName();
        this.films = language.getFilms();
    }

    public Language(){

    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}