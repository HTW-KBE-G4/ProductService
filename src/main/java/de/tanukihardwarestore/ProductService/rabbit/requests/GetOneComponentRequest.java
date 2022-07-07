package de.tanukihardwarestore.ProductService.rabbit.requests;


import java.io.Serializable;

public class GetOneComponentRequest implements Serializable {

    private Long componentID;

    public GetOneComponentRequest(Long componentID) {
        this.componentID = componentID;
    }


    public GetOneComponentRequest() {}

    public Long getComponentID() {
        return componentID;
    }

    public void setComponentID(Long componentID) {
        this.componentID = componentID;
    }
}
