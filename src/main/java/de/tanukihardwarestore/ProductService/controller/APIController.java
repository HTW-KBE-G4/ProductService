package de.tanukihardwarestore.ProductService.controller;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class APIController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    @Qualifier("productExchange")
    private DirectExchange productExchange;

    @Autowired
    @Qualifier("productQueue")
    private Queue productQueue;

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

    @GetMapping("rabit")
    public String publichMessage() {
        String message = "Hello Workds";
        System.out.println("APIController: Send messageto queue: "+ productQueue.getName() + ", message: "+message);

        return (String) this.rabbitTemplate.convertSendAndReceive(productExchange.getName(), "product", message);
    }
}
