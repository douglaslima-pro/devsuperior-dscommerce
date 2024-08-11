package edu.douglaslima.dscommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.douglaslima.dscommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
