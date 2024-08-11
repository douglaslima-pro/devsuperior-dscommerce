package edu.douglaslima.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.douglaslima.dscommerce.dto.ProductDTO;
import edu.douglaslima.dscommerce.entity.Product;
import edu.douglaslima.dscommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id).get();
		return new ProductDTO(product);
	}
	
}
