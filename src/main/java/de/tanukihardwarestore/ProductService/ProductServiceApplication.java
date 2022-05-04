package de.tanukihardwarestore.ProductService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static final String URL_PATH = "http://warehouse:3002/component";
	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

}
