package com.wilson.rabbitmq;

import com.wilson.rabbitmq.producer.RabbitProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private RabbitProducer rabbitProducer;

    @Test
    public void contextLoads() {
    }

    @Test
    public void sendMsg () {
        rabbitProducer.send("hello wilson", null);
    }

}
