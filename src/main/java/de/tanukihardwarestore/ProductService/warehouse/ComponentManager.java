package de.tanukihardwarestore.ProductService.warehouse;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;

import java.util.Collection;

public interface ComponentManager {

    /**
     * Get all components fetched from backend
     * @return a collection of all components fetched
     */
    Collection<PCComponent> getAllComponents();

    /**
     * Init connection. Fetch components and products data from Warehouse-Microservice
     * @return true if all needed data has been fetched successfully, false if the data wasn't complete
     */
    boolean fetchDataFromBackend();

    /**
     * Get all products fetched from backend
     * @return a collection of all products fetched
     */
    Collection<Product> getAllProducts();
}