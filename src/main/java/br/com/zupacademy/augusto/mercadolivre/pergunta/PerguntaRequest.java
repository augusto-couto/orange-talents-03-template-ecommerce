package br.com.zupacademy.augusto.mercadolivre.pergunta;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	private String titulo;

	@Deprecated
	public PerguntaRequest() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public PerguntaRequest(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {
		return new Pergunta(titulo, produto, usuario);
	}
}
