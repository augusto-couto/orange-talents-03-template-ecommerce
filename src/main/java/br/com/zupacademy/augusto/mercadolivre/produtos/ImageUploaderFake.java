package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Profile("dev")
public class ImageUploaderFake implements ImageUploader{

	public Set<String> buildLink(List<MultipartFile> imagens) {
		return imagens.stream().map(
				imagem -> "http://augusto.fakeuploader/"
				+ imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}

}
