package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;

@Component
public class UltimosPassos {

	public void enviaNota(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idComprador", compra.getComprador().getId());
		
		restTemplate.postForEntity("http://localhost:8080/notasfiscais", request, String.class);
	}
	
	public void enviaRanking(Compra compra) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> request = Map.of("idCompra", compra.getId(),
				"idVendedor", compra.getProdutoDesejado().getDono().getId());
		
		restTemplate.postForEntity("http://localhost:8080/ranking", request, String.class);
	}

}
