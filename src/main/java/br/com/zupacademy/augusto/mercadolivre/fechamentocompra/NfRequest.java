package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotNull;

public class NfRequest {
	@NotNull
	private Long idCompra;
	@NotNull
	private Long idComprador;
	
	public NfRequest(@NotNull Long idCompra, @NotNull Long idComprador) {
		super();
		this.idCompra = idCompra;
		this.idComprador = idComprador;
	}

	public Long getIdCompra() {
		return idCompra;
	}

	public Long getIdComprador() {
		return idComprador;
	}
}
