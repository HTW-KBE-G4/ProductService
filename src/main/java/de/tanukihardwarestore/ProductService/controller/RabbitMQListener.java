package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.configurations.RabbitMQConfig;
import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.rabbit.requests.ProductServiceRequest;
import de.tanukihardwarestore.ProductService.rabbit.requests.ProductServiceRequestSingle;
import de.tanukihardwarestore.ProductService.rabbit.results.ComponentQueueResult;
import de.tanukihardwarestore.ProductService.rabbit.results.ProductServiceResult;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class RabbitMQListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.PRODUCT_QUEUE)
    public ProductServiceResult getAllProducts(ProductServiceRequest request) {
        System.out.println("[ProductService]: getAllProducts: <"+request+">");

        ProductServiceResult productServiceResult = new ProductServiceResult(
                this.productRepository.findAll()
                        .stream()
                        .filter(x -> { return x.getUser_id().equals(request.getUserID()); })
                        .collect(Collectors.toList())
        );
        return productServiceResult;
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.PRODUCT_QUEUE)
    public Product getOneProduct(ProductServiceRequestSingle requestSingle) {
        System.out.println("[ProductService]: gotOneProduct: <"+requestSingle+">");

        Product product = this.productRepository.findById(requestSingle.getProductID())
                .orElseThrow();

        if (product.getUser_id().equals(requestSingle.getUserID())) {
            return product;
        }
        return null;
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.PRODUCT_QUEUE)
    public void postProduct(Product product) {
        System.out.println("[ProductService]: postProduct: <"+product+">");

        this.productRepository.save(product);
    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.COMPONENT_QUEUE)
    public ComponentQueueResult getAllComponents(String message) {
        System.out.println("[ProductService]: gotAllComponents: <"+message+">");

        ComponentQueueResult result = new ComponentQueueResult(this.componentRepository.findAll());
        return result;

    }

    @RabbitHandler
    @RabbitListener(queues = RabbitMQConfig.COMPONENT_QUEUE)
    public PCComponent getOneComponents(Long component_id) {
        System.out.println("[ProductService]: gotOneComponent: <"+component_id+">");

        return this.componentRepository.findById(component_id)
                .orElseThrow();
    }

}
