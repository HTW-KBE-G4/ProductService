package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.services.ProductRepositoryService;
import de.tanukihardwarestore.ProductService.services.warehouse.ComponentManager;
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

    @Autowired
    private ProductRepositoryService productRepositoryService;


    @GetMapping("")
    public List<Product> getProducts() {
        return this.productRepositoryService.getAll();
        //return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return this.productRepositoryService.getById(id);
        //return productRepository.findById(id)
        //        .orElse(null);
    }

    @PostMapping()
    public void postProduct(@RequestBody Product product) {
        this.productRepositoryService.save(product);
        //this.productRepository.save(product);
    }


}
