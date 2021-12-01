package com.example.loginservice.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    public static final String queueName = "PasswordExpiry-queue";
    public static final String topicExchangeName = "topic-exchange";


    /**
     * We define our queue info
     *
     * @return Queue object
     */
    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    /**
     * Creates a topic exchange
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }


    /**
     * Bind topic exchange to a queue
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        // bind a queue to a Topic Exchange with routing key
        return BindingBuilder.bind(queue).to(exchange).with("demo.#");
    }


}
