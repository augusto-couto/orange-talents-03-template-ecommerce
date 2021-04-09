package br.com.zupacademy.augusto.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zupacademy.augusto.mercadolivre.annotation.ValorUnico;

public class CategoriaRequest {
	
	@NotBlank
	@ValorUnico(clazz = Categoria.class, field = "nome")
	private String nome;
	private Long idCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria convert(EntityManager entityManager) {
		Categoria categoria = new Categoria(nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = entityManager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "O id da categoriaMae não pode ser nulo!");
			categoria.setCategoriaMae(categoriaMae);
		}
		return categoria;
	}
}
