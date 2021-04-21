package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

public enum StatusRetornoPagSeguro {
	SUCESSO, ERRO;

	public StatusPagamento confere() {
		if (this.equals(SUCESSO)) {
			return StatusPagamento.sucesso;
		}
		return StatusPagamento.erro;
	}
}
