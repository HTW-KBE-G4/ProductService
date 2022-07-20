package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.warehouse.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        checkIfRepositoryIsFilled();
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getComponent(@PathVariable Long id) {
        checkIfRepositoryIsFilled();
        return productRepository.findById(id)
                .orElse(null);
    }

    @PostMapping()
    public void postProduct(@RequestBody Product product) {
        checkIfRepositoryIsFilled();
        this.productRepository.save(product);
    }

    private void checkIfRepositoryIsFilled() {
        if (productRepository.count() <= 0) {
            if (componentManager.fetchDataFromBackend() == false) {
                System.out.println("Error fetching data from WarehouseService...");
            }
        }
    }
}
