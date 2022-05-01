package de.tanukihardwarestore.ProductService.warehouse;

import java.util.Collection;
import java.util.List;

public interface ComponentManager {

    /**
     * Get all Components from backend
     * @return
     */
    Collection<PCComponent> getAll();

    /**
     * Get specific Component by api call
     * @param id id of the compoennt
     * @return
     */
    PCComponent getByID(Long id);

    /**
     * Init connection and fetch the data from Warehouse-Microservice
     * @return
     */
    boolean fetchData(String url);
}