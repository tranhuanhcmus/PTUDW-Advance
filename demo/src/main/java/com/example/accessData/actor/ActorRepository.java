package com.example.accessData.actor;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActorRepository extends CrudRepository<Actor, Short> {
    public Optional<Actor> findByFirstNameAndLastName(String firstName, String lastName);
}