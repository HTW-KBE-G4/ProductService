package de.tanukihardwarestore.ProductService.repository;

import de.tanukihardwarestore.ProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
