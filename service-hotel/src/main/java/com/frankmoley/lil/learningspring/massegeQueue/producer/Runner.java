
package com.frankmoley.lil.learningspring.massegeQueue.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.frankmoley.lil.learningspring.data.entity.Room;
import com.frankmoley.lil.learningspring.data.repository.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class);
    @Value("${spring.rabbitmq.queues.queue-one.name}")
    private String QueueName;
    private final ConfigurableApplicationContext context;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RoomRepository repository;

    @Autowired
    public Runner(ConfigurableApplicationContext context, RabbitTemplate rabbitTemplate, ObjectMapper objectMapper, RoomRepository repository) {
        this.context = context;
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
        this.repository = repository;
    }




    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Sending Messages");
        Iterable<Room> roomIterable = repository.findAll();
        roomIterable.forEach(room -> {
            try {
                LOGGER.info("Clean My Room" + room.getRoomNumber());
                String roomJson = objectMapper.writeValueAsString(room);
                rabbitTemplate.convertAndSend(QueueName, roomJson);
                Thread.sleep(100);
            } catch (JsonProcessingException | InterruptedException e) {
                LOGGER.info("parsing error", e);
            }

        });

    }
}

