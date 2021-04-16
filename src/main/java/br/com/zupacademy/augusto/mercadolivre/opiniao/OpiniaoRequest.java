package br.com.zupacademy.augusto.mercadolivre.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

public class OpiniaoRequest {
	
	@Min(1)
	@Max(5)
	@NotNull
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Length(max = 500)
	private String descricao;

	public OpiniaoRequest(@Min(1) @Max(5) @NotNull int nota, @NotBlank String titulo,
			@NotBlank @Length(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(@NotNull Produto produto, @NotNull Usuario usuario) {
		return new Opiniao(this.nota, this.titulo, this.descricao, produto, usuario);
	}
}
