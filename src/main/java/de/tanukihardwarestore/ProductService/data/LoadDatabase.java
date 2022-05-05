package de.tanukihardwarestore.ProductService.data;

import de.tanukihardwarestore.ProductService.ProductServiceApplication;
import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import de.tanukihardwarestore.ProductService.warehouse.ComponentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

import java.util.List;

@Configuration
public class LoadDatabase {

    private static final String CSV_FILE_PATH = "classpath:/products.csv";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ComponentManager componentManager;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ComponentRepository repository) {


        return args -> {
            repository.deleteAll();

            if (componentManager.fetchData() == false) {
                log.error("Failed fetching componnets from backend into database");
            }

        };
    }

    @Bean
    CommandLineRunner initProducts(ProductRepository repository) {

        return args -> {
            repository.deleteAll();

            if (componentManager.fetchData() == false) {
                log.error("Failed fetching products from backend into database");
            }
        };
    }
}
