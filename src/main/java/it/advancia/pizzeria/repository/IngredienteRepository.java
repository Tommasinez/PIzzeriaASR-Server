package it.advancia.pizzeria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.advancia.pizzeria.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository <Ingrediente, Long> {

}
