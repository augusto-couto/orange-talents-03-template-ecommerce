package br.com.zupacademy.augusto.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioRequest {
	@NotBlank
	@Email
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
}
