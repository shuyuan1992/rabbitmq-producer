package com.wilson.rabbitmq;

import com.wilson.rabbitmq.producer.RabbitProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RequestMapping("/api/hello")
@RestController
public class RabbitmqApplication {

    @Autowired
    private RabbitProducer rabbitProducer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqApplication.class, args);
    }

    @RequestMapping
    public String testMsgSender() {
        rabbitProducer.send("hello wilson", null);
        return "success";
    }
}
