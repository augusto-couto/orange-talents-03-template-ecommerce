package br.com.zupacademy.augusto.mercadolivre.detalhaproduto;

import javax.persistence.EntityManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;

@RestController
public class DetalhaProdutoController {
	
	private EntityManager entityManager;

	public DetalhaProdutoController(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}
	
	@GetMapping("/produtos/{id}")
	public DetalhaProduto detalhar(@PathVariable("id") Long id) {
		Produto produto = entityManager.find(Produto.class, id);
		return new DetalhaProduto(produto);
	}
}
