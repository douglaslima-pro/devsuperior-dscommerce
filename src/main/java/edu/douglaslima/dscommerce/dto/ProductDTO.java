package edu.douglaslima.dscommerce.dto;

import edu.douglaslima.dscommerce.entity.Product;

public record ProductDTO(Long id, String name, String description, Double price, String imgUrl) {

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
