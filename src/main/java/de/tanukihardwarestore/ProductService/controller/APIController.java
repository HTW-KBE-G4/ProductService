package de.tanukihardwarestore.ProductService.controller;

import de.tanukihardwarestore.ProductService.configurations.RabbitMQConfig;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("")
public class APIController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectExchange exchange;

    @Autowired
    private Queue queue;

    public APIController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("")
    public String getWelcomeMessage() {
        return "<h1>Welcome to the Product Service!</h1>" +
                "<br>" +
                "<a href='http://localhost:4200/components'>compoennts</a>" +
                "<br>" +
                "<a href='http://localhost:4200/products'>products</a>" +
                "<br>" +
                "<a href='http://localhost:4200/h2-console'>H2-Console</a>";
    }

    @GetMapping("/rabit")
    public String publichMessage() {
        String message = "Hello Workds";
        System.out.println("APIController: Send messageto queue: "+queue.getName() + ", message: "+message);

        return (String) this.rabbitTemplate.convertSendAndReceive(exchange.getName(), "product", message);
    }
}
