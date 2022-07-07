package de.tanukihardwarestore.ProductService.rabbit.results;

import de.tanukihardwarestore.ProductService.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceResult implements Serializable {

    public ProductServiceResult(List<Product> productList) {
        this.productList = productList;
    }

    private List<Product> productList;

    public ProductServiceResult() {}

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductServiceResult{" +
                "productList=" + productList +
                '}';
    }
}
