package de.tanukihardwarestore.ProductService.rabbit.results;

import de.tanukihardwarestore.ProductService.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceResult {

    private List<Product> productList;

    public ProductServiceResult(List<Product> productList) {
        this.productList = new ArrayList<>(productList);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
