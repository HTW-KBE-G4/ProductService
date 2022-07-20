package de.tanukihardwarestore.ProductService.rabbit.requests;

import java.io.Serializable;

public class ProductServiceRequestSingle implements Serializable {
    private long productID;

    private String userID;

    public ProductServiceRequestSingle() { }

    public ProductServiceRequestSingle(String userID, long productID) {
        this.productID = productID;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
