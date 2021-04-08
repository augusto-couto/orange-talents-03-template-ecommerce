package br.com.zupacademy.augusto.mercadolivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@PostMapping
	@Transactional
	public String cadastrar(@RequestBody @Valid UsuarioRequest request) {
		Usuario usuario = request.converte();
		entityManager.persist(usuario);
		return usuario.toString();
	}
}
