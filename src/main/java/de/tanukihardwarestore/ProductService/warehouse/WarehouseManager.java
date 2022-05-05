package de.tanukihardwarestore.ProductService.warehouse;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WarehouseManager implements ComponentManager {

    private static final String COMPONENT_PATH = "http://warehouse:3002/components";

    private static final String PRODUCT_PATH = "http://warehouse:3002/products";

    @Autowired
    private ComponentRepository componentRepository;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Collection<PCComponent> getAllComponents() {
        return this.componentRepository.findAll();
    }

    @Override
    public PCComponent getComponentByID(Long id) {
        return this.componentRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public Collection<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public boolean fetchData() {
        return fetchComponents() && fetchProducts();
    }

    private boolean fetchComponents() {
        if (this.componentRepository.count() > 0) {
            //list has already been fetched. no more need to...
            return true;
        }

        ResponseEntity<List<PCComponent>> response = restTemplate.exchange(
                COMPONENT_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PCComponent>>() { }
        );

        List<PCComponent> list = response.getBody();

        if (list != null) {
            this.componentRepository.saveAll(list);
            return true;
        }

        //got null reponse -> return false...
        return false;
    }

    private boolean fetchProducts() {
        if (this.productRepository.count() > 0) {
            return true;
        }

        ResponseEntity<List<Product>> response = restTemplate.exchange(
                PRODUCT_PATH,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Product>>() { }
        );

        List<Product> productList  = response.getBody();

        if (productList != null) {
            this.productRepository.saveAll(productList);
            return true;
        }

        return false;
    }
}
