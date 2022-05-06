package de.tanukihardwarestore.ProductService.warehouse;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;

import java.util.Collection;

public interface ComponentManager {

    /**
     * Get all Components from backend
     * @return all Components
     */
    Collection<PCComponent> getAllComponents();

    /**
     * Init connection and fetch components and products data from Warehouse-Microservice
     * @return true if all needed data has been fetched successfully, false if the data wasn't complete
     */
    boolean fetchData();

    /**
     * Get all products from backend
     * @return all products
     */
    Collection<Product> getAllProducts();
}