package edu.douglaslima.dscommerce.dto;

import edu.douglaslima.dscommerce.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductDTO(
		Long id,
		@Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres")
		@NotBlank(message = "Campo obrigatório")
		String name,
		@Size(min = 10, message = "Descrição deve ter no mínimo 10 caracteres")
		@NotBlank(message = "Campo obrigatório")
		String description,
		@Positive(message = "O preço deve ser positivo")
		Double price,
		String imgUrl) {

	public ProductDTO(Product product) {
		this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
	}
	
	public Product toEntity() {
		return new Product(id, name, description, price, imgUrl);
	}
	
	public void copyToEntity(Product product) {
		product.setName(name);
		product.setDescription(description);
		product.setPrice(price);
		product.setImgUrl(imgUrl);
	}
	
}
