package br.com.zupacademy.augusto.mercadolivre.compra;

import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayCompra {
	
	pagseguro {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPagseguro = uriComponentsBuilder
					.path("/pagamento-pagseguro/{id}")
					.buildAndExpand(compra.getId()).toString();

			return "pagseguro.com/" + compra.getId() + "?redirectUrl="
					+ urlRetornoPagseguro;
		}
	},
	paypal {
		@Override
		String criaUrlRetorno(Compra compra,
				UriComponentsBuilder uriComponentsBuilder) {
			String urlRetornoPaypal = uriComponentsBuilder
					.path("/pagamento-paypal/{id}").buildAndExpand(compra.getId())
					.toString();

			return "paypal.com/" + compra.getId() + "?redirectUrl=" + urlRetornoPaypal;
		}
	};

	abstract String criaUrlRetorno(Compra compra,
			UriComponentsBuilder uriComponentsBuilder);
}
