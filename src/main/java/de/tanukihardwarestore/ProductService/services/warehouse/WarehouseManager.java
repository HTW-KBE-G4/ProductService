package de.tanukihardwarestore.ProductService.services.warehouse;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.services.ProductRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Service
public class WarehouseManager implements ComponentManager {

    private static final String COMPONENT_PATH = "http://warehouse:3002/components";

    private static final String PRODUCT_PATH = "http://warehouse:3002/products";

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Collection<PCComponent> getAllComponents() {
        ResponseEntity<List<PCComponent>> response = restTemplate.exchange(
                COMPONENT_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PCComponent>>() { }
        );

        List<PCComponent> list = response.getBody();

        return list;
    }

    @Override
    public Collection<Product> getAllProducts() {
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                PRODUCT_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() { }
        );

        List<Product> productList  = response.getBody();

        return productList;
    }
}
