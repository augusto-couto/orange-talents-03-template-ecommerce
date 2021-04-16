package br.com.zupacademy.augusto.mercadolivre.pergunta;

import javax.persistence.EntityManager;
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
public class PerguntaController {
	
	private EntityManager entityManager;
	private TokenManager tokenManager;
	private Emails email;

	public PerguntaController(EntityManager entityManager, TokenManager tokenManager, Emails email) {
		super();
		this.email = email;
		this.entityManager = entityManager;
		this.tokenManager = tokenManager;
	}

	@PostMapping("/produtos/{id}/perguntas")
	public ResponseEntity<Pergunta> adicionaPergunta(@PathVariable("id") Long id, @RequestBody @Valid PerguntaRequest request,
			@RequestHeader HttpHeaders header) {
		Produto produto = entityManager.find(Produto.class, id);
		String token = header.getFirst(HttpHeaders.AUTHORIZATION);
		Usuario usuario = entityManager.find(Usuario.class,
				tokenManager.getIdUsuario(token.substring(7, token.length())));
		
		Pergunta pergunta = request.toModel(produto, usuario);
		email.enviaNovaPergunta(pergunta);
		return ResponseEntity.ok(pergunta);
	}
}
