package com.example.accessData.actor;

import org.springframework.data.repository.CrudRepository;

import com.example.accessData.actor.Actor;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Short> {
    public Optional<Actor> findByFirstNameAndLastName(String firstName, String lastName);
}