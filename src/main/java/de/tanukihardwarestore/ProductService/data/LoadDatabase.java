package de.tanukihardwarestore.ProductService.data;

import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
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

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {



        CSVDataReader csvDataReader = new CSVDataReader(this.resourceLoader);
        List<Product> list = csvDataReader.getObjects(CSV_FILE_PATH);


        return args -> {

            repository.deleteAll();

            for (Product product :
                    list) {

                log.info("preloading..."+repository.save(product));
            }
        };
    }
}
