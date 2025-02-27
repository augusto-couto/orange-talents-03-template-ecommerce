package br.com.zupacademy.augusto.mercadolivre.autenticacao;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenManager {

	@Value("${mercado-livre.jwt.expiration}")
	private String expiration;
	
	@Value("${mercado-livre.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		Date dataAtual = new Date();
		Date dataExpiracao = new Date(dataAtual.getTime() + Long.parseLong(expiration));
		return Jwts.builder()
				.setIssuer("API do mercado livre.")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(dataAtual)
				.setExpiration(dataExpiracao)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
