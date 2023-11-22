package com.example.rabbitmqdemo.producer;

import com.example.rabbitmqdemo.config.RabbitMQConfig;
import com.example.rabbitmqdemo.entity.Order;
import com.example.rabbitmqdemo.entity.OrderDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order")
    public OrderDTO placeOrder(@RequestBody Order order) {
        OrderDTO orderDTO = new OrderDTO(order, "Order Placed", "Hi Producer Your Order is Placed ");
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE, RabbitMQConfig.ROUTING_KEY, orderDTO);
        return orderDTO;
    }
}
