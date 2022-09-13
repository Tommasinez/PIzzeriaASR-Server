package it.advancia.pizzeria.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.advancia.pizzeria.model.*;
import it.advancia.pizzeria.repository.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/pizzeria")
public class PizzeriaController {
	
	@Autowired
	PizzaRepository pizzaRepository;
	
	@Autowired
	ImpastoRepository impastoRepository;
	
	@Autowired
	IngredienteRepository ingredienteRepository;
	

	@GetMapping("/pizze")
	public ResponseEntity<List<Pizza>> getAllPizze() {
		try {
			List<Pizza> listaPizze = new ArrayList<Pizza>();
			pizzaRepository.findAll().forEach(listaPizze::add);
			listaPizze.forEach(pizza -> {
				pizza.getImpasto().setPizza(null);
				pizza.getUtente().setPizza(null);
				pizza.getUtente().setRoles(null);
				pizza.getIngredienti().forEach(ingrediente -> ingrediente.setPizza(null));
			});
			return new ResponseEntity<>(listaPizze, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/pizze/{id}")
	public ResponseEntity<Pizza> getPizzaById(@PathVariable("id") Long id) {
		Optional<Pizza> pizzaId = pizzaRepository.findById(id);
		if (pizzaId.isPresent()) {
			Pizza pizza = pizzaId.get();
			pizza.getImpasto().setPizza(null);
			pizza.getUtente().setPizza(null);
			pizza.getUtente().setRoles(null);
			pizza.getIngredienti().forEach(ingrediente -> ingrediente.setPizza(null));
			return new ResponseEntity<>(pizza, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/pizze")
	public ResponseEntity<Pizza> createPizza(@RequestBody Pizza newPizza) {
		try {
			Pizza pizza = pizzaRepository
					.save(new Pizza(newPizza.getNome(), newPizza.getImpasto(), newPizza.getUtente(), newPizza.getIngredienti()));
			return new ResponseEntity<>(pizza, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/pizze/{id}")
	public ResponseEntity<Pizza> updatePizza(@PathVariable("id") long id, @RequestBody Pizza updatedPizza) {
		Optional<Pizza> pizzaId = pizzaRepository.findById(id);
		if (pizzaId.isPresent()) {
			Pizza pizza = pizzaId.get();
			pizza.setNome(updatedPizza.getNome());
			pizza.setImpasto(updatedPizza.getImpasto());
			pizza.setIngredienti(updatedPizza.getIngredienti());
			return new ResponseEntity<>(pizzaRepository.save(pizza), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/pizze/{id}")
	public ResponseEntity<Pizza> deletePizza(@PathVariable("id") long id) {
		try {
			pizzaRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/impasti")
	public ResponseEntity<List<Impasto>> getAllImpasti() {
		try {
			List<Impasto> listaImpasti = new ArrayList<Impasto>();
			impastoRepository.findAll().forEach(listaImpasti::add);
			listaImpasti.forEach(impasto -> {
				impasto.setPizza(null);
			});
			return new ResponseEntity<>(listaImpasti, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/ingredienti")
	public ResponseEntity<List<Ingrediente>> getAllIngredienti() {
		try {
			List<Ingrediente> listaIngredienti = new ArrayList<Ingrediente>();
			ingredienteRepository.findAll().forEach(listaIngredienti::add);
			listaIngredienti.forEach(ingrediente -> {
				ingrediente.setPizza(null);
			});
			return new ResponseEntity<>(listaIngredienti, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
