package br.com.zupacademy.augusto.mercadolivre.opiniao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.mercadolivre.autenticacao.TokenManager;
import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {
	
	private EntityManager entityManager;
	private TokenManager tokenManager;

	public OpiniaoController(EntityManager entityManager, TokenManager tokenManager) {
		super();
		this.entityManager = entityManager;
		this.tokenManager = tokenManager;
	}

	@PostMapping("/produtos/{id}/opiniao")
	@Transactional
	public ResponseEntity<Opiniao> adicionaOpiniao(@Valid @RequestBody OpiniaoRequest request, @PathVariable("id") Long id,
			@RequestHeader HttpHeaders header) {
		String token = header.getFirst(HttpHeaders.AUTHORIZATION);
		Usuario usuario = entityManager.find(Usuario.class,
				tokenManager.getIdUsuario(token.substring(7, token.length())));
		
		Produto produto = entityManager.find(Produto.class, id);
		Opiniao opiniao = request.toModel(produto, usuario);
		entityManager.persist(opiniao);
		return ResponseEntity.ok(opiniao);
	}
}
