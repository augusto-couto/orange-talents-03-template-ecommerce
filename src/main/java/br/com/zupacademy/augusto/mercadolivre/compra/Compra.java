package br.com.zupacademy.augusto.mercadolivre.compra;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.util.Assert;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.augusto.mercadolivre.fechamentocompra.Pagamento;
import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Entity
public class Compra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	@NotNull
	private Produto produtoDesejado;
	@Positive
	@NotNull
	private Integer quantidadeDesejada;
	@ManyToOne 
	@NotNull
	private Usuario comprador;
	@NotNull
	@Enumerated
	private GatewayCompra gatewayCompra;
	@NotNull
	@Positive
	private BigDecimal valorNoMomentoDaCompra;
	@NotNull
	@Enumerated
	private StatusCompra statusCompra;
	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Pagamento> pagamentos = new HashSet<>();
	
	@Deprecated
	public Compra() {
		super();
	}

	public Compra(@NotNull Produto produtoDesejado, @Positive @NotNull Integer quantidadeDesejada,
			@NotNull Usuario comprador, @NotNull GatewayCompra gatewayCompra,  
			@NotNull @Positive BigDecimal valorNoMomentoDaCompra, @NotNull StatusCompra statusCompra) {
		super();
		this.produtoDesejado = produtoDesejado;
		this.quantidadeDesejada = quantidadeDesejada;
		this.comprador = comprador;
		this.gatewayCompra = gatewayCompra;
		this.valorNoMomentoDaCompra = valorNoMomentoDaCompra;
		this.statusCompra = statusCompra;
	}
	
	public String urlRedirecionamento(
			UriComponentsBuilder uriComponentsBuilder) {
		return this.gatewayCompra.criaUrlRetorno(this, uriComponentsBuilder);
	}

	public Long getId() {
		return id;
	}
	public Produto getProdutoDesejado() {
		return produtoDesejado;
	}
	public Integer getQuantidadeDesejada() {
		return quantidadeDesejada;
	}
	public Usuario getComprador() {
		return comprador;
	}
	public GatewayCompra getGatewayCompra() {
		return gatewayCompra;
	}
	public BigDecimal getValorNoMomentoDaCompra() {
		return valorNoMomentoDaCompra;
	}
	public StatusCompra getStatusCompra() {
		return statusCompra;
	}
	public Set<Pagamento> getPagamentos() {
		return pagamentos;
	}
	public void setStatusCompra(StatusCompra statusCompra) {
		this.statusCompra = statusCompra;
	}

	public void registraTentativaPagamento(@Valid GatewayPagamento request) {
		Pagamento pagamento = request.toPagamento(this);
		
		Assert.isTrue(!this.pagamentos.contains(pagamento), 
				"Este pagamento ja foi registrado!");
		
		Set<Pagamento> pagamentosBemSucedidos = this.pagamentos.stream()
				.filter(Pagamento :: bemSucedida).collect(Collectors.toSet());
		
		Assert.isTrue(pagamentosBemSucedidos.isEmpty(),
				"Essa compra ja foi finalizada!");
		
		this.pagamentos.add(request.toPagamento(this));
	}

	@Override
	public String toString() {
		return "Compra [id=" + id + ", produtoDesejado=" + produtoDesejado + ", quantidadeDesejada="
				+ quantidadeDesejada + ", comprador=" + comprador + ", gatewayCompra=" + gatewayCompra
				+ ", valorNoMomentoDaCompra=" + valorNoMomentoDaCompra + ", statusCompra=" + statusCompra
				+ ", pagamentos=" + pagamentos + "]";
	}
}
