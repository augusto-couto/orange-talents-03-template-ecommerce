package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagensRequest {

	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();

	public ImagensRequest(@Size(min = 1) List<MultipartFile> imagens) {
		super();
		this.imagens = imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
}
