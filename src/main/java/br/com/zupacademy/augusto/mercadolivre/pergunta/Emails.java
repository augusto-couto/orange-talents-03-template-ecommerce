package br.com.zupacademy.augusto.mercadolivre.pergunta;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import br.com.zupacademy.augusto.mercadolivre.produtos.Produto;
import br.com.zupacademy.augusto.mercadolivre.usuario.Usuario;

@Component
@Profile("dev")
public class Emails {

	public void enviaNovaPergunta(Pergunta pergunta) {
		System.out.println("Um email para " + pergunta.getEmailDonoProduto() + " com a pergunta " + pergunta.getTitulo());
	}

	public void enviaPossivelComprador(Usuario comprador, Produto produtoDesejado) {
		System.out.println("Um email para " + produtoDesejado.getDono().getUsername()
				+ " sobre possível venda do produto " + produtoDesejado.getNome()
				+ " para o comprador " + comprador.getUsername());
		
	}

}
