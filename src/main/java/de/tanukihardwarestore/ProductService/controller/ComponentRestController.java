package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.ProductServiceApplication;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.warehouse.ComponentManager;
import de.tanukihardwarestore.ProductService.warehouse.PCComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("component")
public class ComponentRestController {

    @Autowired
    private ComponentManager componentManager;
    
    @Autowired
    private ComponentRepository componentRepository;

    @GetMapping("")
    public List<PCComponent> getAllComponents() {
        checkIfRepositoryIsFilled();
        return componentRepository.findAll();
    }

    @GetMapping("/{id}")
    public PCComponent getComponent(@PathVariable Long id) {
        checkIfRepositoryIsFilled();
        return componentRepository.findById(id)
                .orElseThrow();
    }

    /**
     * Fills repository if it wasn't already filled during startup bean
     */
    private void checkIfRepositoryIsFilled() {
        if (componentRepository.count() <= 0) {
            if (componentManager.fetchData(ProductServiceApplication.URL_PATH) == true) {
                componentRepository.saveAll(componentManager.getAll());
            }
            else {
                System.out.println("Error fetching data from WarehouseService...");
            }
        }
    }
}
