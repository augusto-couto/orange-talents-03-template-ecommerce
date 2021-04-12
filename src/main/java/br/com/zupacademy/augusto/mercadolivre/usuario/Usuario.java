package br.com.zupacademy.augusto.mercadolivre.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import br.com.zupacademy.augusto.mercadolivre.autenticacao.Perfil;

@Entity
public class Usuario implements UserDetails{

	private static final long serialVersionUID = 1L;
	
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	@Deprecated
	public Usuario() {
		super();
	}

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

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
