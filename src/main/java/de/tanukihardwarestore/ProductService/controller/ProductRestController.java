package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.warehouse.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentManager componentManager;


    @GetMapping("")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getComponent(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow();
    }

}
