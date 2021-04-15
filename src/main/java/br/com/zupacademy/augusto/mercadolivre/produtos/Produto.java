package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.augusto.mercadolivre.categoria.Categoria;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@PositiveOrZero
	private Integer quantidadeDisponivel;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@ManyToOne
	@NotNull
	private Categoria categoria;
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	@Size(min = 3)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	@PastOrPresent
	@CreationTimestamp
	@NotNull
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@NotNull
	@ManyToOne
	private Usuario dono;
	
	@Deprecated
	public Produto() {
		super();
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@PositiveOrZero Integer quantidadeDisponivel, @NotBlank @Length(max = 1000) String descricao,
			@NotNull Categoria categoria, @Size(min = 3) Set<CaracteristicaProdutoRequest> caracteristicas, @NotNull Usuario dono) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
		this.caracteristicas.addAll(caracteristicas
				.stream().map(caracteristica -> caracteristica.converte(this))
				.collect(Collectors.toSet()));
		this.dono = dono;
		
		Assert.hasText(this.nome, "Nome não pode ser nulo e deve conter ao menos um caractere que não seja espaço em branco!");
		Assert.isTrue(this.valor.doubleValue() > 0, "Valor precisa ser maior que zero");
		Assert.notNull(this.valor, "Valor não pode ser nulo!");
		Assert.isTrue(this.quantidadeDisponivel >= 0, "Quantidade deve ser maior ou igual a zero!");
		Assert.hasText(this.descricao, "Descricao não pode ser nulo e deve conter ao menos um caractere que não seja espaço em branco!");
		Assert.isTrue(this.descricao.length() <= 1000, "Descricao deve ter no maximo mil caracteres!");
		Assert.notNull(this.categoria, "Categoria não pode ser nulo!");
		Assert.isTrue(this.caracteristicas.size() >= 3, "Produto precisa de no mínimo três características para ser cadastrado!");
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Usuario getDono() {
		return dono;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void setImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> 
		new ImagemProduto(this, link))
		.collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean belongToUser(Usuario possivelDono) {
		return this.dono.equals(possivelDono) ;
	}
}
