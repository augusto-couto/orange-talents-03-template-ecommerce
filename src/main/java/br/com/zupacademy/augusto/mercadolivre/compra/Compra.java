package br.com.zupacademy.augusto.mercadolivre.compra;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	@NotNull
	private Produto produtoDesejado;
	@Positive
	@NotNull
	private Integer quantidadeDesejada;
	@ManyToOne 
	@NotNull
	private Usuario comprador;
	@NotNull
	@Enumerated
	private GatewayCompra gatewayCompra;
	@NotNull
	@Positive
	private BigDecimal valorNoMomentoDaCompra;
	@NotNull
	@Enumerated
	private StatusCompra statusCompra;
	
	public Compra(@NotNull Produto produtoDesejado, @Positive @NotNull Integer quantidadeDesejada,
			@NotNull Usuario comprador, @NotNull GatewayCompra gatewayCompra,  
			@NotNull @Positive BigDecimal valorNoMomentoDaCompra, @NotNull StatusCompra statusCompra) {
		super();
		this.produtoDesejado = produtoDesejado;
		this.quantidadeDesejada = quantidadeDesejada;
		this.comprador = comprador;
		this.gatewayCompra = gatewayCompra;
		this.valorNoMomentoDaCompra = valorNoMomentoDaCompra;
		this.statusCompra = statusCompra;
	}

	public Long getId() {
		return id;
	}
}
