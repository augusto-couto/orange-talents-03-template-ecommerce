package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;
import br.com.zupacademy.augusto.mercadolivre.compra.GatewayPagamento;
import br.com.zupacademy.augusto.mercadolivre.compra.StatusCompra;

public class PagSeguroRequest implements GatewayPagamento{
	@NotBlank
	private String idPagamento;
	@NotNull
	private StatusRetornoPagSeguro status;
	
	public PagSeguroRequest(@NotBlank String idPagamento, @NotNull StatusRetornoPagSeguro status) {
		super();
		this.idPagamento = idPagamento;
		this.status = status;
	}

	
	public Pagamento toPagamento(Compra compra) {
		if (this.status == StatusRetornoPagSeguro.SUCESSO) {
			compra.setStatusCompra(StatusCompra.EM_PROGRESSO);
		}
		return new Pagamento(status.confere(), idPagamento, compra);
	}
}
