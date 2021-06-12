package com.example.springboot_rabbitmq.consumer;

import com.example.springboot_rabbitmq.config.MessagingConfiguration;
import com.example.springboot_rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @RabbitListener(queues = MessagingConfiguration.QUEUE)
    public void consumeMessageFromQueue(OrderStatus orderStatus) {
        System.out.println("Message received from queue : " + orderStatus);
    }
}
