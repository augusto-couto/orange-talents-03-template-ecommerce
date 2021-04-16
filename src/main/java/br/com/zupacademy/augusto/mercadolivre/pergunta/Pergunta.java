package br.com.zupacademy.augusto.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String titulo;
	@PastOrPresent
	@CreationTimestamp
	@NotNull
	private LocalDateTime instantePergunta = LocalDateTime.now();
	@ManyToOne
	@NotNull
	private Produto produto;
	@ManyToOne
	@NotNull
	private Usuario usuario;
	
	public Pergunta(@NotBlank String titulo, @NotNull Produto produto, @NotNull Usuario usuario) {
		super();
		this.titulo = titulo;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDateTime getInstantePergunta() {
		return instantePergunta;
	}

	public Produto getProduto() {
		return produto;
	}
	
	public String getEmailDonoProduto() {
		return produto.getDono().getUsername();
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
