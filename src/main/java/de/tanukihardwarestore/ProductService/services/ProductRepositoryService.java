package de.tanukihardwarestore.ProductService.services;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.services.externalApi.PixabayProductImageManager;
import de.tanukihardwarestore.ProductService.services.warehouse.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Product> getAll(String user_id) {

        List<Product> productList = this.productRepository.findAll();
        return filterListByUserId(productList, user_id);
    }

    public Product getById(Long id, String user_id) {
        Product product = this.productRepository.findById(id)
                .orElse(null);

        if (checkForValidUserId(product, user_id)) {
            return product;
        }
        return null;
    }

    public void saveAll(List<Product> productList) {
        for (Product product: productList) {
            this.save(product);
        }
    }

    public void deleteAll() {
        this.productRepository.deleteAll();
    }

    private List<Product> filterListByUserId(List<Product> productList, String user_id) {
        return productList.stream()
                .filter(x -> x.getUser_id().equals(user_id))
                .collect(Collectors.toList());
    }

    private boolean checkForValidUserId(Product product, String user_id) {
        return product.getUser_id().equals(user_id);
    }
}
