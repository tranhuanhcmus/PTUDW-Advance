package com.example.accessData.actor;

import com.example.accessData.filmActor.FilmActor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.LinkedHashSet;
import java.util.Set;
//@CrossOrigin("*")
@Entity
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @OneToMany(mappedBy = "actor")
    private Set<FilmActor> filmActors = new LinkedHashSet<>();

    public Actor(ActorDTO actorDTO){
        this.firstName=actorDTO.getFirstName();
        this.lastName=actorDTO.getLastName();
    }

    public Actor() {

    }

    public Short getId() {
        return id;
    }

    public void setId(Short actor_id) {
        this.id = actor_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<FilmActor> getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(Set<FilmActor> filmActors) {
        this.filmActors = filmActors;
    }
}
