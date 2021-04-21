package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SistemasExternos {

	@PostMapping("/notasfiscais")
	public void enviaNota(@Valid @RequestBody NfRequest request) {
		System.out.println("Criando nota da compra " + request.getIdCompra() + " efetuada por " + request.getIdComprador());
	}
	
	@PostMapping("/ranking")
	public void enviaRanking(@Valid @RequestBody RankingRequest request) {
		System.out.println("Criando nota da compra " + request.getIdCompra() + " emitida por " + request.getIdVendedor());
	}
}
