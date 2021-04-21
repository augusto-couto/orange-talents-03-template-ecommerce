package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;

public class RankingRequest {
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idVendedor;
	
	public RankingRequest(@NotNull Long idCompra, @NotNull Long idVendedor) {
		super();
		this.idCompra = idCompra;
		this.idVendedor = idVendedor;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdVendedor() {
		return idVendedor;
	}
}
