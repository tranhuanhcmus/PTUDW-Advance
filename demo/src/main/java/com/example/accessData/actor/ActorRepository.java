package com.example.accessData.actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ActorRepository extends JpaRepository<Actor, Short>, JpaSpecificationExecutor<Actor> {
}