package br.com.zupacademy.augusto.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.zupacademy.augusto.mercadolivre.annotation.ValorUnico;

public class UsuarioRequest {
	
	@NotBlank
	@Email
	@ValorUnico(field = "login", clazz = Usuario.class)
	private String login;
	@NotBlank
	@Length(min = 6)
	private String senha;
	
	public UsuarioRequest(@NotBlank @Email String login,
			@NotBlank @Length(min = 6) String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}
	
	public Usuario converte() {
		return new Usuario(this.login, this.senha);
	}

	public UsernamePasswordAuthenticationToken convertToToken() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
