package de.tanukihardwarestore.ProductService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tanukihardwarestore.ProductService.configurations.RabbitMQConfig;
import de.tanukihardwarestore.ProductService.model.PCComponent;
import de.tanukihardwarestore.ProductService.model.Product;
import de.tanukihardwarestore.ProductService.rabbit.requests.GetAllComponentsRequest;
import de.tanukihardwarestore.ProductService.rabbit.requests.GetOneComponentRequest;
import de.tanukihardwarestore.ProductService.rabbit.requests.ProductServiceRequest;
import de.tanukihardwarestore.ProductService.rabbit.requests.ProductServiceRequestSingle;
import de.tanukihardwarestore.ProductService.rabbit.results.ComponentQueueResult;
import de.tanukihardwarestore.ProductService.rabbit.results.ProductServiceResult;
import de.tanukihardwarestore.ProductService.repository.ComponentRepository;
import de.tanukihardwarestore.ProductService.repository.ProductRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RabbitMQListener {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @RabbitListener(queues = RabbitMQConfig.PRODUCT_QUEUE)
    public String getAllProducts(ProductServiceRequest request) {
        System.out.println("[ProductService]: getAllProducts: <"+request.getUserID()+">");
        /*ProductServiceResult productServiceResult = new ProductServiceResult(
                this.productRepository.findAll()
                        .stream()
                        .filter(x -> x.getUser_id().equals(request.getUserID()))
                        .collect(Collectors.toList())
        );*/
        ProductServiceResult productServiceResult = new ProductServiceResult(this.productRepository.findAll().stream().toList());

        String resultString = "";
        //if (product.getUser_id().equals(requestSingle.getUserID())) {
        //    return this.writeJSON(product);
        //}
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resultString = objectMapper.writeValueAsString(productServiceResult);
        } catch (JsonProcessingException e) {
            System.out.println("Unable to write JSON String of: ");
        }
        System.out.println("[ProductService]: getAllProducts: sending resulti:<"+productServiceResult+">");
        return resultString;
    }

    @RabbitListener(queues = RabbitMQConfig.SINGLE_PRODUCT_QUEUE)
    public String getOneProduct(ProductServiceRequestSingle requestSingle) {
        System.out.println("[ProductService]: gotOneProduct: <"+requestSingle+">");

        Product product = this.productRepository.findById(requestSingle.getProductID())
                .orElse(new Product());
        String resultString = "";
        //if (product.getUser_id().equals(requestSingle.getUserID())) {
        //    return this.writeJSON(product);
        //}
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            resultString = objectMapper.writeValueAsString(product);
        } catch (JsonProcessingException e) {
            System.out.println("Unable to write JSON String of: ");
        }

        return resultString;
    }

    @RabbitListener(queues = RabbitMQConfig.CREATE_PRODUCT_QUEUE)
    public void postProduct(Product product) {
        System.out.println("[ProductService]: postProduct: <"+product+">");

        this.productRepository.save(product);
    }

    @RabbitListener(queues = RabbitMQConfig.COMPONENT_QUEUE)
    public String getAllComponents(GetAllComponentsRequest request) throws JsonProcessingException {
        System.out.println("[ProductService]: gotAllComponents: <"+request.getMessage()+">");

        ComponentQueueResult result = new ComponentQueueResult(this.componentRepository.findAll());
        String resultString;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            resultString = objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return resultString;
    }

    private String writeJSON(Serializable object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = RabbitMQConfig.SINGLE_COMPONENT_QUEUE)
    public String getOneComponents(GetOneComponentRequest request) throws JsonProcessingException {
        System.out.println("[ProductService]: gotOneComponent: <"+request.getComponentID()+">");

        PCComponent pcComponent = this.componentRepository.findById(request.getComponentID())
                .orElse(new PCComponent());

        return this.writeJSON(pcComponent);
    }
}
