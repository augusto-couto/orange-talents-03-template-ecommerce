package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.net.URI;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.augusto.mercadolivre.autenticacao.TokenManager;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@RestController
public class ProdutosController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private ImageUploader imageUploader;
	private TokenManager tokenManager;
	
	public ProdutosController(ImageUploader imageUploader, TokenManager tokenManager) {
		this.imageUploader = imageUploader;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping("/produtos")
	@Transactional
	public ResponseEntity<ProdutoResponse> cadastrar(@RequestBody @Valid ProdutoRequest request, UriComponentsBuilder uriBuilder,
			@RequestHeader HttpHeaders header) {
		String token = header.getFirst(HttpHeaders.AUTHORIZATION);
		Long idUsuario = tokenManager.getIdUsuario(token.substring(7, token.length()));
		Usuario dono = entityManager.find(Usuario.class, idUsuario);
		Produto produto = request.convert(entityManager, dono);
		entityManager.persist(produto);
		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
	}
	
	@PostMapping("/produtos/{id}/imagens")
	@Transactional
	public ResponseEntity<Produto> adicionaImagem(@PathVariable("id") Long id, @Valid ImagensRequest imagensRequest, @RequestHeader HttpHeaders header) {
		
		Produto produto = entityManager.find(Produto.class, id);
		String token = header.getFirst(HttpHeaders.AUTHORIZATION);
		Long idUsuario = tokenManager.getIdUsuario(token.substring(7, token.length()));
		Usuario currentUser = entityManager.find(Usuario.class, idUsuario);
		
		if (!produto.belongToUser(currentUser)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Só é possível adicionar uma imagem a um produto que te pertence");
		}
		
		Set<String> links = imageUploader.buildLink(imagensRequest.getImagens());
		produto.setImagens(links);
		entityManager.merge(produto);
		return ResponseEntity.ok(produto);
	}
}
