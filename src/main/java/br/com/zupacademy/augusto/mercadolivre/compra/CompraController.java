package br.com.zupacademy.augusto.mercadolivre.compra;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.augusto.mercadolivre.pergunta.Emails;
import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@RestController
public class CompraController {

	private EntityManager entityManager;
	private Emails emails;

	public CompraController(EntityManager entityManager, Emails emails) {
		super();
		this.entityManager = entityManager;
		this.emails = emails;
	}

	@PostMapping("/compras")
	@Transactional
	public ResponseEntity<String> efetuaCompra(@Valid @RequestBody CompraRequest compraRequest,
			@AuthenticationPrincipal Usuario comprador, UriComponentsBuilder uriBuilder) {
		Produto produtoDesejado = entityManager.find(Produto.class, compraRequest.getIdProdutoDesejado());
		boolean temEstoque = produtoDesejado.consomeEstoque(compraRequest.getQuantidadeDesejada());

		if (temEstoque) {
			emails.enviaPossivelComprador(comprador, produtoDesejado);
			
			Compra compra = new Compra(produtoDesejado, compraRequest.getQuantidadeDesejada(), comprador,
					compraRequest.getGatewayCompra(), produtoDesejado.getValor(), StatusCompra.INICIADA);
			entityManager.persist(compra);

			switch (compraRequest.getGatewayCompra()) {
			case pagseguro:
				String urlRetornoPagseguro = uriBuilder.path("/pagamento-pagseguro/{id}")
				.buildAndExpand(compra.getId())
						.toString();
				return ResponseEntity
						.ok("pagseguro.com?returnId=" + compra.getId()
						+ "&redirectUrl=" + urlRetornoPagseguro);
			case paypal:
				String urlRetornopaypal = uriBuilder.path("/pagamento-paypal/{id}")
				.buildAndExpand(compra.getId())
						.toString();
				return ResponseEntity.ok("paypal.com?buyerId=" + compra.getId()
				+ "&redirectUrl" + urlRetornopaypal);
			default:
				break;
			}
		}
		System.out.println(comprador);
		return ResponseEntity.badRequest().body("Faltam produtos no estoque");
	}

}
