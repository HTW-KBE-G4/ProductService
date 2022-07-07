package de.tanukihardwarestore.ProductService.configurations;

import de.tanukihardwarestore.ProductService.controller.RabbitMQListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    public static final String PRODUCT_QUEUE = "product";
    public static final String SINGLE_PRODUCT_QUEUE = "single.product";

    public static final String COMPONENT_QUEUE = "component";

    public static final String SINGLE_COMPONENT_QUEUE = "single.component";

    public static final String CREATE_PRODUCT_QUEUE = "product.create";

    private static class ReceiverConfig {

        @Bean
        @Qualifier("productQueue")
        public Queue productQueue() {
            return new Queue(PRODUCT_QUEUE, false);
        }

        @Bean
        @Qualifier("productExchange")
        public DirectExchange productExchange() {
            return new DirectExchange(PRODUCT_QUEUE);
        }



        @Bean
        public Binding bindProduct(@Qualifier("productExchange") DirectExchange productExchange,
                                   @Qualifier("productQueue") Queue productQueue) {
            return BindingBuilder.bind(productQueue)
                    .to(productExchange)
                    .with(PRODUCT_QUEUE);
        }
    }

    private static class SingleProductQueueConfig {
        @Bean
        @Qualifier("singleProductExchange")
        public DirectExchange singleProductExchange() {
            return new DirectExchange(SINGLE_PRODUCT_QUEUE);
        }



        @Bean
        public Binding bindSingleProduct(@Qualifier("singleProductExchange") DirectExchange exchange,
                                         @Qualifier("singleProductQueue") Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(SINGLE_PRODUCT_QUEUE);
        }

        @Bean
        @Qualifier("singleProductQueue")
        public Queue singleProductQueue() {
            return new Queue(SINGLE_PRODUCT_QUEUE, false);
        }
    }

    private static class SingleComponentQueueConfig {
        @Bean
        @Qualifier("singleComponentExchange")
        public DirectExchange singleComponentExchange() {
            return new DirectExchange(SINGLE_COMPONENT_QUEUE);
        }



        @Bean
        public Binding bindSingleComponent(@Qualifier("singleComponentExchange") DirectExchange exchange,
                                           @Qualifier("singleComponentQueue") Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(SINGLE_COMPONENT_QUEUE);
        }

        @Bean
        @Qualifier("singleComponentQueue")
        public Queue singleComponentQueue() {
            return new Queue(SINGLE_COMPONENT_QUEUE, false);
        }
    }

    private static class ComponentConfig {

        @Bean
        @Qualifier("componentExchange")
        public DirectExchange componentExchange() {
            return new DirectExchange(COMPONENT_QUEUE);
        }



        @Bean
        public Binding bindComponent(@Qualifier("componentExchange") DirectExchange componentExchange,
                                     @Qualifier("componentQueue") Queue componentQueue) {
            return BindingBuilder.bind(componentQueue)
                    .to(componentExchange)
                    .with(COMPONENT_QUEUE);
        }

        @Bean
        @Qualifier("componentQueue")
        public Queue componentQueue() {
            return new Queue(COMPONENT_QUEUE, false);
        }
    }

    private static class ProductCreateQueueConfig {

        @Bean
        @Qualifier("createProductExchange")
        public DirectExchange createProductExchange() {
            return new DirectExchange(CREATE_PRODUCT_QUEUE);
        }



        @Bean
        public Binding bindCreateProduct(@Qualifier("createProductExchange") DirectExchange exchange,
                                     @Qualifier("createProductQueue") Queue queue) {
            return BindingBuilder.bind(queue)
                    .to(exchange)
                    .with(COMPONENT_QUEUE);
        }

        @Bean
        @Qualifier("createProductQueue")
        public Queue createProductQueue() {
            return new Queue(CREATE_PRODUCT_QUEUE, false);
        }
    }

    @Bean
    public RabbitMQListener receiver() {
        return new RabbitMQListener();
    }


}
