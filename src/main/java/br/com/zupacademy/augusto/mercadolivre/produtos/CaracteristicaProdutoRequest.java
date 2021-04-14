package br.com.zupacademy.augusto.mercadolivre.produtos;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	
	public CaracteristicaProdutoRequest(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public CaracteristicaProduto converte(Produto produto) {
		return new CaracteristicaProduto(this.nome, this.descricao, produto);
	}
}
