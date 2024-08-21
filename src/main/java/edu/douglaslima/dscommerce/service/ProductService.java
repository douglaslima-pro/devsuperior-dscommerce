package edu.douglaslima.dscommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.douglaslima.dscommerce.dto.ProductDTO;
import edu.douglaslima.dscommerce.entity.Product;
import edu.douglaslima.dscommerce.repository.ProductRepository;
import edu.douglaslima.dscommerce.service.exception.DatabaseException;
import edu.douglaslima.dscommerce.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recurso não encontrado!"));
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
		try {
			Product product = productRepository.getReferenceById(id);
			productDTO.copyToEntity(product);
			product = productRepository.save(product);
			return new ProductDTO(product);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso não encontrado!");
		}
	}
	
	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Recurso não encontrado!");
		}
		try {
			productRepository.deleteById(id);;
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Erro de integridade referencial!");
		}
	}

}
