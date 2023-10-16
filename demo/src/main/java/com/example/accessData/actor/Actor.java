package com.example.accessData.actor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

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
}
