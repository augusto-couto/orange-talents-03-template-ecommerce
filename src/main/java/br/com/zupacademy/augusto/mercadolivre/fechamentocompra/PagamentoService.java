package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import javax.persistence.EntityManager;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;
import br.com.zupacademy.augusto.mercadolivre.compra.GatewayPagamento;
import br.com.zupacademy.augusto.mercadolivre.compra.StatusCompra;
import br.com.zupacademy.augusto.mercadolivre.pergunta.Emails;

@Service
public class PagamentoService {
	
	private EntityManager entityManager;
	private UltimosPassos ultimosPassos;
	private Emails emails;
	
	PagamentoService(EntityManager entityManager, UltimosPassos ultimosPassos, Emails emails) {
		this.entityManager = entityManager;
		this.ultimosPassos = ultimosPassos;
		this.emails = emails;
	}
	
	public ResponseEntity<Compra> fechaCompra(Long idCompra, GatewayPagamento gatewayPagamento) {
		Compra compraEmProcesso = entityManager.find(Compra.class, idCompra);
		compraEmProcesso.registraTentativaPagamento(gatewayPagamento);

		if (compraEmProcesso.getStatusCompra() == StatusCompra.EM_PROGRESSO) {
			ultimosPassos.enviaNota(compraEmProcesso);
			ultimosPassos.enviaRanking(compraEmProcesso);
			compraEmProcesso.setStatusCompra(StatusCompra.FINALIZADA);
			emails.emailCompraFinalizada(compraEmProcesso);
		} else {
			emails.tentarComprarNovamente(compraEmProcesso);
		}
	
		entityManager.merge(compraEmProcesso);
		return ResponseEntity.ok(compraEmProcesso);
	}
}
