package de.tanukihardwarestore.ProductService.data;

import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.services.ProductRepositoryService;
import de.tanukihardwarestore.ProductService.services.warehouse.ComponentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class DatabaseLoader {

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ComponentManager componentManager;

    @Autowired
    private ProductRepositoryService productRepositoryService;

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(ComponentRepository repository) {

        return args -> {
            repository.deleteAll();
            repository.saveAll(this.componentManager.getAllComponents());

        };
    }

    @Bean
    CommandLineRunner initProducts(ProductRepository repository) {

        return args -> {
            this.productRepositoryService.deleteAll();
            this.productRepositoryService.saveAll(this.componentManager.getAllProducts().stream().toList());
        };
    }
}
