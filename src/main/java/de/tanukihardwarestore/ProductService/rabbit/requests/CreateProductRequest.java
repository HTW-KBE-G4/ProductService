package de.tanukihardwarestore.ProductService.rabbit.requests;


import de.tanukihardwarestore.ProductService.model.Product;

import java.io.Serializable;

public class CreateProductRequest implements Serializable {
    private Product product;
    private String userID;

    public CreateProductRequest(Product product, String userID) {
        this.product = product;
        this.userID = userID;
    }

    public Product getProduct() {
        return product;
    }

    public String getUserID() {
        return userID;
    }
}
