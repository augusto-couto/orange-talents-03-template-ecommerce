package br.com.zupacademy.augusto.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(1)
	@Max(5)
	@NotNull
	int nota;
	@NotBlank
	String titulo;
	@NotBlank
	@Length(max = 500)
	String descricao;
	@NotNull
	@ManyToOne
	Produto produto;
	@NotNull
	@ManyToOne
	Usuario usuario;

	public Long getId() {
		return id;
	}

	public int getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Produto getProduto() {
		return produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Opiniao(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao,
			@NotNull Produto produto, @NotNull Usuario usuario) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}
	
}
