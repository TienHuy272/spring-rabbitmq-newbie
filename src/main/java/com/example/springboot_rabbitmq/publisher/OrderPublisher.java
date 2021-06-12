package com.example.springboot_rabbitmq.publisher;

import com.example.springboot_rabbitmq.config.MessagingConfiguration;
import com.example.springboot_rabbitmq.dto.Order;
import com.example.springboot_rabbitmq.dto.OrderStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
    @Autowired
    private RabbitTemplate template;

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order,@PathVariable String restaurantName) {
        order.setOrderId(UUID.randomUUID().toString());
        OrderStatus orderStatus = new OrderStatus(order, "Process", "order placed successfully " + restaurantName);
        template.convertAndSend(MessagingConfiguration.EXCHANGE, MessagingConfiguration.ROUTING_KEY, orderStatus);
        return "success";
    }
}
