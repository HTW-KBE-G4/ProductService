package de.tanukihardwarestore.ProductService.repository;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<PCComponent, Long> {
}
