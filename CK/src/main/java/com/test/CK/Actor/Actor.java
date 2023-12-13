package com.test.CK.Actor;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity()
@Table(name = "actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short id;

    @Column(name = "first_name")
    @NotEmpty(message = "firstName is not null")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "lastName is not null")
    private String lastName;


    public Actor(Short id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actor() {

    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
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
