package br.com.zupacademy.augusto.mercadolivre.fechamentocompra;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.augusto.mercadolivre.compra.Compra;

@Entity
public class Pagamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	StatusPagamento status;
	@NotBlank 
	String idGateway;
	@PastOrPresent
	@CreationTimestamp
	@NotNull
	private LocalDateTime instanteCadastro;
	@NotNull
	@ManyToOne
	private Compra compra;
	
	@Deprecated
	public Pagamento() {
		super();
	}

	public Long getId() {
		return id;
	}

	public StatusPagamento getStatus() {
		return status;
	}

	public String getIdGateway() {
		return idGateway;
	}

	public LocalDateTime getInstanteCadastro() {
		return instanteCadastro;
	}

	public Pagamento(StatusPagamento status, @NotBlank String idGateway, @NotNull Compra compra) {
		this.status = status;
		this.idGateway = idGateway;
		this.instanteCadastro = LocalDateTime.now();
		this.compra = compra;
	}
	
	public boolean bemSucedida() {
		return this.status.equals(StatusPagamento.sucesso);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGateway == null) ? 0 : idGateway.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (idGateway == null) {
			if (other.idGateway != null)
				return false;
		} else if (!idGateway.equals(other.idGateway))
			return false;
		return true;
	}
}
