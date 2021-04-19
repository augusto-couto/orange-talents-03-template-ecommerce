package br.com.zupacademy.augusto.mercadolivre.pergunta;

public class PerguntaDetalhesResponse {
	
	private String titulo;

	public PerguntaDetalhesResponse(Pergunta pergunta) {
		super();
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
}
