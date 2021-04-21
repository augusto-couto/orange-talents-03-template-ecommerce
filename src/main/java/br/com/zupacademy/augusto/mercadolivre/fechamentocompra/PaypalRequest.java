package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;
import br.com.zupacademy.augusto.mercadolivre.compra.GatewayPagamento;
import br.com.zupacademy.augusto.mercadolivre.compra.StatusCompra;

public class PaypalRequest implements GatewayPagamento{

	@NotBlank
	private String idPagamento;
	@NotNull
	@Min(0)
	@Max(1)
	private int status;

	public PaypalRequest(@NotBlank String idPagamento, @Min(0) @Max(1) @NotNull int status) {
		super();
		this.idPagamento = idPagamento;
		this.status = status;
	}

	public Pagamento toPagamento(Compra compra) {
		if (this.status == 1) {
			compra.setStatusCompra(StatusCompra.EM_PROGRESSO);
		}
		return new Pagamento(this.status == 0 ? StatusPagamento.erro 
				: StatusPagamento.sucesso, idPagamento, compra);
	}
}
