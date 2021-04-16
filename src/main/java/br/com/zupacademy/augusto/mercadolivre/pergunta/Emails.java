package br.com.zupacademy.augusto.mercadolivre.pergunta;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class Emails {

	public void enviaNovaPergunta(Pergunta pergunta) {
		System.out.println("Um email para " + pergunta.getEmailDonoProduto() + " com a pergunta " + pergunta.getTitulo());
	}

}
