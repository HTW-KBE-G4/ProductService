package de.tanukihardwarestore.ProductService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class APIController {

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
}
