package br.com.zupacademy.augusto.mercadolivre.autenticacao;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {
	private String email;
	private String senha;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

}
