package it.advancia.pizzeria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ingrediente")
public class Ingrediente {

	@Id
	@Column(name="id_ingrediente")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_ingrediente")
	private String nome;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Pizza> pizza;
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Pizza> getPizza() {
		return pizza;
	}
	
	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
}

