package com.example.accessData.film;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FilmRepository extends JpaRepository<Film, Short>, JpaSpecificationExecutor<Film> {
}