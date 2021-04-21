package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;

@RestController
public class PagamentoPagSeguroController {
	
	private PagamentoService pagamentoService;
	
	public PagamentoPagSeguroController(PagamentoService pagamentoService) {
		this.pagamentoService = pagamentoService;
	}
	
	@PostMapping("/pagamento-pagseguro/{id}")
	@Transactional
	public ResponseEntity<Compra> fechaCompraPagSeguro(@PathVariable("id") Long idCompra,
			@Valid @RequestBody PagSeguroRequest request) {

		return pagamentoService.fechaCompra(idCompra, request);
	}
}
