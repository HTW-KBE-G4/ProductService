package de.tanukihardwarestore.ProductService.rabbit.results;


import de.tanukihardwarestore.ProductService.model.PCComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponentQueueResult implements Serializable {

    private List<PCComponent> pcComponentList;

    public ComponentQueueResult(List<PCComponent> pcComponentList) {
        this.pcComponentList = new ArrayList<>(pcComponentList);
    }

    public ComponentQueueResult() {}

    public List<PCComponent> getPcComponentList() {
        return pcComponentList;
    }

    public void setPcComponentList(List<PCComponent> pcComponentList) {
        this.pcComponentList = pcComponentList;
    }
}
