package it.advancia.pizzeria.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pizza")
	private Long id;
	
	@Column(name="nome_pizza")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name="id_impasto", nullable=false)
	private Impasto impasto;
	
	@ManyToOne
	@JoinColumn(name="id_utente", nullable=false)
	private Utente utente;
	
	@ManyToMany(targetEntity = Ingrediente.class)
	@JoinTable(name = "Pizza_Ingredienti", 
				joinColumns = { @JoinColumn(name = "id_pizza") }, 
				inverseJoinColumns = { @JoinColumn(name = "id_ingrediente") })
	private List<Ingrediente> ingredienti;
	
	public Pizza(){}
	
	public Pizza(String nome, Impasto impasto, Utente utente, List<Ingrediente> ingredienti){
		this.nome = nome;
		this.impasto = impasto;
		this.utente = utente;
		this.ingredienti = ingredienti;
	}

	public Pizza(String nome) {
		this.nome = nome;
	}

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

	public Impasto getImpasto() {
		return impasto;
	}
	public void setImpasto(Impasto impasto) {
		this.impasto = impasto;
	}
	
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	public List<Ingrediente> getIngredienti() {
		return ingredienti;
	}
	
	public void setIngredienti(List<Ingrediente> ingredienti) {
		this.ingredienti = ingredienti;
	}
}
