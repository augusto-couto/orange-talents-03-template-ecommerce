package br.com.zupacademy.augusto.mercadolivre.compra;

import br.com.zupacademy.augusto.mercadolivre.fechamentocompra.Pagamento;

public interface GatewayPagamento {
	Pagamento toPagamento(Compra compra);
}
