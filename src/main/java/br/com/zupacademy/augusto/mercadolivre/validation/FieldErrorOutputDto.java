package br.com.zupacademy.augusto.mercadolivre.validation;

public class FieldErrorOutputDto {
	private String campo;
	private String erro;
	
	public FieldErrorOutputDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
}
