package br.com.zupacademy.augusto.mercadolivre.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.augusto.mercadolivre.autenticacao.TokenManager;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;
import br.com.zupacademy.augusto.mercadolivre.usuario.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenManager tokenManager;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFilter(TokenManager tokenManager, UsuarioRepository usuarioRepository) {
		this.tokenManager = tokenManager;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenManager.isTokenValido(token);
		
		if (valido) {
			autenticarCliente(token);
		}
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenManager.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
