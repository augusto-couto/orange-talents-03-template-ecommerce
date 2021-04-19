package br.com.zupacademy.augusto.mercadolivre.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {
	
	@Positive
	@NotNull
	private Integer quantidadeDesejada;
	@NotNull
	private Long idProdutoDesejado;
	@NotNull
	private GatewayCompra gatewayCompra;

	public CompraRequest(@Positive @NotNull Integer quantidadeDesejada, @NotNull Long idProdutoDesejado,
			@NotNull GatewayCompra gatewayCompra) {
		super();
		this.quantidadeDesejada = quantidadeDesejada;
		this.idProdutoDesejado = idProdutoDesejado;
		this.gatewayCompra = gatewayCompra;
	}
	
	public Integer getQuantidadeDesejada() {
		return quantidadeDesejada;
	}
	public Long getIdProdutoDesejado() {
		return idProdutoDesejado;
	}
	public GatewayCompra getGatewayCompra() {
		return gatewayCompra;
	}
}
