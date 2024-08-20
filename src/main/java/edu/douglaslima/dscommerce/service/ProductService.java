package edu.douglaslima.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAll(Pageable pageable) {
		Page<Product> result = productRepository.findAll(pageable);
		return result.map(product -> new ProductDTO(product));
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO productDTO) {
		Product product = productDTO.toEntity();
		product = productRepository.save(product);
		return new ProductDTO(product);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO productDTO) {
		Product product = productRepository.getReferenceById(id);
		productDTO.copyToEntity(product);
		product = productRepository.save(product);
		return new ProductDTO(product);
	}
	
	@Transactional
	public void delete(Long id) {
		productRepository.deleteById(id);;
	}

}
