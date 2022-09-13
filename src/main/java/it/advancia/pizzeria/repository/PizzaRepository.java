package it.advancia.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.advancia.pizzeria.model.*;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
