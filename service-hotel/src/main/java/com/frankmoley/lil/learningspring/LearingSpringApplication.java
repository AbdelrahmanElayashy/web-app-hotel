package com.frankmoley.lil.learningspring;


import com.frankmoley.lil.learningspring.massegeQueue.consumer.CleanRoomProcessor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;




@SpringBootApplication
public class LearingSpringApplication {
    @Value("${spring.rabbitmq.queues.queue-one.name}")
    private String queueName;
    @Value("${spring.rabbitmq.exchanges.exchange-one.name}")
    private String exchagneQ;

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(exchagneQ);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }


    @Bean
    public MessageListenerAdapter messageListenerAdapter(CleanRoomProcessor processor) {
        return new MessageListenerAdapter(processor, "reciveMessage");
    }

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory factory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(factory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    public static void main(String[] args) {

        SpringApplication.run(LearingSpringApplication.class, args);
    }

}
