package br.com.zupacademy.augusto.mercadolivre.autenticacao;

public class TokenRespose {
	
	private String token;
	private String tipo;
	
	public TokenRespose(String token, String tipo) {
		this.token = token;
		this.tipo = tipo;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}
}
