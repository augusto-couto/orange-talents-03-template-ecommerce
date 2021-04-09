package br.com.zupacademy.augusto.mercadolivre.categoria;

public class CategoriaResponse {
	
	private Long id;
	private String nome;
	private Categoria categoriaMae;
	
	public CategoriaResponse(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
		this.categoriaMae = categoria.getCategoriaMae();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}
}
