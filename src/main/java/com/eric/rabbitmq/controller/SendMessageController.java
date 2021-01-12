package com.eric.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by wangjunf
 * @Description send message controller
 * @Date 2021/1/7 23:43
 */
@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("message")
    public String sendMessage(@RequestBody String message) {

        rabbitTemplate.convertAndSend("direct.exchange", "direct.routing", message.getBytes());

        return "ok";
    }
}
