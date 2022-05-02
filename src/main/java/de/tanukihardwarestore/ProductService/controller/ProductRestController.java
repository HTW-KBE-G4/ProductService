package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.warehouse.ComponentManager;
import de.tanukihardwarestore.ProductService.warehouse.PCComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductRestController {

    private static final String URL_PATH = "http://localhost:3002/component";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentManager componentManager;

    /*@GetMapping("help")
    public String getWelcomeMessage() {
        return "<h1>Welcome to the Product Service!</h1>" +
                "<br>" +
                "<a href='http://localhost:4200/component'>compoennts</a>" +
                "<br>" +
                "<a href='http://localhost:4200/product'>products</a>" +
                "<br>" +
                "<a href='http://localhost:4200/h2-console'>H2-Console</a>";
    }*/

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
