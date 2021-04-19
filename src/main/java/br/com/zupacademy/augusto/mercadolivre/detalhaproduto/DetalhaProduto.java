package br.com.zupacademy.augusto.mercadolivre.detalhaproduto;

import java.math.BigDecimal;
import java.util.Set;
import br.com.zupacademy.augusto.mercadolivre.opiniao.Opiniao;
import br.com.zupacademy.augusto.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.augusto.mercadolivre.produtos.CaracteristicaProduto;
import br.com.zupacademy.augusto.mercadolivre.produtos.ImagemProduto;
import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;

public class DetalhaProduto {
	
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private Integer totalOpinioes;
	private Set<CaracteristicaProduto> caracteristicas;
	private Set<ImagemProduto> imagens;
	private Set<Pergunta> perguntas;
	private Set<Opiniao> opinioes;
	private Double mediaNotas;
	private Integer quantidade;
	private String vendidoPor;
	
	public DetalhaProduto(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.preco = produto.getValor();
		this.totalOpinioes = produto.getOpinioes().size();
		this.caracteristicas = produto.getCaracteristicas();
		this.imagens = produto.getImagens();
		this.perguntas = produto.getPerguntas();
		this.opinioes = produto.getOpinioes();
		
		Set<Integer> notas = produto.mapOpinioes(opiniao -> opiniao.getNota());
		this.mediaNotas = notas.stream().mapToInt(nota -> nota).average().orElse(0.0);
		this.quantidade = produto.getQuantidadeDisponivel();
		this.vendidoPor = produto.getDono().getUsername();
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}
	public String getNome() {
		return nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public Integer getTotalOpinioes() {
		return totalOpinioes;
	}
	public Set<CaracteristicaProduto> getCaracteristicas() {
		return caracteristicas;
	}
	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}
	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}
	public double getMediaNotas() {
		return mediaNotas;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public String getVendidoPor() {
		return vendidoPor;
	}
}
