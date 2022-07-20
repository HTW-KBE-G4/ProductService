package de.tanukihardwarestore.ProductService.services;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.services.externalApi.PixabayProductImageManager;
import de.tanukihardwarestore.ProductService.services.warehouse.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRepositoryService {

    @Autowired
    private ComponentManager componentManager;
    @Autowired
    private PixabayProductImageManager pixabayProductImageManager;

    @Autowired
    private ProductRepository productRepository;

    public void save(Product product) {
        if (product.getImage_url() == null || product.getImage_url().equals("")) {
            product.setImage_url(pixabayProductImageManager.getImage(product.getProduct_id()));
        }
        this.productRepository.save(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product getById(Long id) {
        return this.productRepository.findById(id)
                .orElse(null);
    }

    public void saveAll(List<Product> productList) {
        for (Product product: productList) {
            this.save(product);
        }
    }

    public long getCount() {
        return this.productRepository.count();
    }

    private void checkIfRepositoryIsFilled() {
        if (productRepository.count() <= 0) {
            this.productRepository.saveAll(this.componentManager.getAllProducts());
        }
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }
}
