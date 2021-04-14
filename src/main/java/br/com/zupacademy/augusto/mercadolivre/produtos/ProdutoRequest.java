package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import br.com.zupacademy.augusto.mercadolivre.categoria.Categoria;

public class ProdutoRequest {
	
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@PositiveOrZero
	private Integer quantidadeDisponivel;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@NotNull
	private Long categoriaId;
	@Size(min = 3)
	private Set<CaracteristicaProdutoRequest> caracteristicas = new HashSet<>();

	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @PositiveOrZero Integer quantidadeDisponivel,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long categoriaId, @Size(min = 3) Set<CaracteristicaProdutoRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}

	public Produto convert(EntityManager entityManager) {
		Categoria categoria = entityManager.find(Categoria.class, categoriaId);
		return new Produto(this.nome, this.valor, this.quantidadeDisponivel,
				this.descricao, categoria, this.caracteristicas);
	}

}
