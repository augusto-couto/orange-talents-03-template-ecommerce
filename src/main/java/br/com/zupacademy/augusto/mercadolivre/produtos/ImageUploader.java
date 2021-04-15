package br.com.zupacademy.augusto.mercadolivre.produtos;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ImageUploader {

	public Set<String> buildLink(List<MultipartFile> imagens);

}
