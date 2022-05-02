package de.tanukihardwarestore.ProductService.controller;

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
    private static final String URL_PATH = "http://localhost:3002/component";
    
    @Autowired
    private ComponentManager componentManager;

    @GetMapping("")
    public List<PCComponent> getcomponents() {
        if (componentManager.fetchData(URL_PATH) == false) {
            return null;
        }
        return componentManager.getAll().stream().toList();
    }

    @GetMapping("/{id}")
    public PCComponent getComponent(@PathVariable Long id) {
        if (componentManager.fetchData(URL_PATH) == false) {
            return null;
        }
        return componentManager.getByID(id);
    }
}
