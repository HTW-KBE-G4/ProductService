package de.tanukihardwarestore.ProductService.services.warehouse;

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
     * Get all products fetched from backend
     * @return a collection of all products fetched
     */
    Collection<Product> getAllProducts();
}