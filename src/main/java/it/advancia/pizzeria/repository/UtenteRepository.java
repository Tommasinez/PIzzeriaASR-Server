package it.advancia.pizzeria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.advancia.pizzeria.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {
	Optional<Utente> findByUsername(String username);
	Boolean existsByUsername(String username);
}