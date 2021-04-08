package br.com.zupacademy.augusto.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Email
	private String login;
	@NotBlank
	@Length(min = 6)
	private String senha;
	@PastOrPresent
	@CreationTimestamp
	@NotNull
	private LocalDateTime instanteCadastro = LocalDateTime.now();
	
	public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		super();
		Assert.hasText(login, 
				"Login não pode ser nulo e deve conter ao menos um caractere que não seja espaço em branco!");
		Assert.hasText(senha, 
				"Senha não pode ser nulo e deve conter ao menos um caractere que não seja espaço em branco!");
		Assert.isTrue(senha.length() > 5, "Senha precisa de no mínimo 6 caracteres!");
		this.login = login;
		this.senha = new BCryptPasswordEncoder(10).encode(senha);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", login=" + login + ", senha=" + senha + ", instanteCadastro=" + instanteCadastro
				+ "]";
	}
}
