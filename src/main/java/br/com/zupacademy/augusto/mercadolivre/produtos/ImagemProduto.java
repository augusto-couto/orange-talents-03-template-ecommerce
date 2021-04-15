package br.com.zupacademy.augusto.mercadolivre.produtos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
public class ImagemProduto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@NotBlank
	@URL
	private String link;
	
	@Deprecated
	public ImagemProduto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public String getLink() {
		return link;
	}

	public ImagemProduto(@NotNull @Valid Produto produto, @NotBlank @URL String link) {
		super();
		this.produto = produto;
		this.link = link;
	}
}
