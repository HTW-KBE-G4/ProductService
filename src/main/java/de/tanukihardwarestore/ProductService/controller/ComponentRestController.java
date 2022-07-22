package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.services.warehouse.ComponentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentRestController {

    @Autowired
    private ComponentManager componentManager;
    
    @Autowired
    private ComponentRepository componentRepository;

    @GetMapping("")
    public List<PCComponent> getAllComponents() {
        fillRepositoryIfEmpty();
        return componentRepository.findAll();
    }

    @GetMapping("/{id}")
    public PCComponent getComponent(@PathVariable Long id) {
        fillRepositoryIfEmpty();
        return componentRepository.findById(id)
                .orElse(null);
    }

    private void fillRepositoryIfEmpty() {
        if (componentRepository.count() <= 0) {
            this.componentRepository.saveAll(this.componentManager.getAllComponents());
        }
    }
}
