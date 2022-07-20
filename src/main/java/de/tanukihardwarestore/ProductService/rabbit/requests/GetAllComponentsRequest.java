package de.tanukihardwarestore.ProductService.rabbit.requests;

import java.io.Serializable;

public class GetAllComponentsRequest implements Serializable {

    private String message;

    public GetAllComponentsRequest(String message) {
        this.message = message;
    }

    public GetAllComponentsRequest() {}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
