package edu.douglaslima.dscommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.douglaslima.dscommerce.dto.ProductDTO;
import edu.douglaslima.dscommerce.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ProductDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
}
