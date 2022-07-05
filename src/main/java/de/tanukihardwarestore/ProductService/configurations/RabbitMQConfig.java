package de.tanukihardwarestore.ProductService.configurations;

import de.tanukihardwarestore.ProductService.controller.RabbitMQListener;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_QUEUE = "product";

    public static final String COMPONENT_QUEUE = "component";

    private static class ReceiverConfig {

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange(PRODUCT_QUEUE);
        }

        @Bean
        public RabbitMQListener receiver() {
            return new RabbitMQListener();
        }

        @Bean
        public Binding binding(DirectExchange exchange,
                               Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(PRODUCT_QUEUE);
        }
    }

    private static class ComponentConfig {

        @Bean
        public DirectExchange exchange() {
            return new DirectExchange(COMPONENT_QUEUE);
        }

        @Bean
        public RabbitMQListener receiver() {
            return new RabbitMQListener();
        }

        @Bean
        public Binding binding(DirectExchange exchange,
                               Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(COMPONENT_QUEUE);
        }
    }

    @Bean
    public Queue productQueue() {
        return new Queue(PRODUCT_QUEUE, false);
    }

    @Bean
    public Queue componentQueue() {
        return new Queue(COMPONENT_QUEUE, false);
    }
}
