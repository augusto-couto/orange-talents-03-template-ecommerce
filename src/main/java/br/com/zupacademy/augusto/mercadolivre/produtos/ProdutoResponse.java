package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.zupacademy.augusto.mercadolivre.categoria.Categoria;

public class ProdutoResponse {
	
	private Long id;
	private String nome;
	private BigDecimal valor;
	private Integer quantidadeDisponivel;
	private String descricao;
	private Categoria categoria;
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	private LocalDateTime instanteCadastro;
	
	public ProdutoResponse(Produto produto) {
		super();
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.quantidadeDisponivel = produto.getQuantidadeDisponivel();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
		this.instanteCadastro = produto.getInstanteCadastro();
		this.caracteristicas.addAll(produto.getCaracteristicas());
	
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}
}
