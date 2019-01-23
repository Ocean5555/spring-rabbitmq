package com.ocean.spring.exchange;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * Created by Ocean on 2019/1/23 13:55.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RabbitAdminTest {
    @Resource
    private RabbitAdmin rabbitAdmin;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void createExchange(){
        rabbitAdmin.declareExchange(new FanoutExchange("test.exchange.fanout" , true , true));
        rabbitAdmin.declareExchange(new DirectExchange("test.exchange.driect" , true , true));
        rabbitAdmin.declareExchange(new TopicExchange("test.exchange.topic" , true , true));
    }

    @Test
    public void createQueueAndBind(){
        rabbitAdmin.declareQueue(new Queue("test.queue"));
        rabbitAdmin.declareBinding(new Binding(
                "test.queue" , Binding.DestinationType.QUEUE,
                "test.exchange.topic" , "#" , new HashMap<String, Object>()));
    }

    @Test
    public void deleteExchange(){
        rabbitAdmin.deleteExchange("test.exchange.fanout");
        rabbitAdmin.deleteExchange("test.exchange.driect");
        rabbitAdmin.deleteExchange("test.exchange.topic");
    }

    @Test
    public void deleteQueue(){
        rabbitAdmin.deleteQueue("test.queue");
    }

    @Test
    public void sendMessage(){
        rabbitTemplate.convertAndSend("test.exchange.topic" , "abc" , "hahaoooooo");
    }

}
