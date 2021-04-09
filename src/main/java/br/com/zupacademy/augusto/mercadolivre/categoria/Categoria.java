package br.com.zupacademy.augusto.mercadolivre.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String nome;
	@ManyToOne
	private Categoria categoriaMae;
	
	@Deprecated
	public Categoria() {
		super();
	}

	public Categoria(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
