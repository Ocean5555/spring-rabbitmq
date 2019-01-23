package com.ocean.spring.exchange;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

/**
 * Created by Ocean on 2019/1/23 12:59.
 */
public class NewsProducer {
    private RabbitTemplate rabbitTemplate;

    public RabbitTemplate getRabbitTemplate() {
        return rabbitTemplate;
    }

    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNews(String routingKey , News news){
        //发送数据，第二个参数：可以是字符串、byte[]或者任何实现了序列化接口的对象
        rabbitTemplate.convertAndSend(routingKey , news);
        System.out.println("news has been send");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        NewsProducer newsProducer = (NewsProducer) applicationContext.getBean("newsProducer");
        newsProducer.sendNews("us.20190101",new News("新华社","特朗普又退群了",new Date() , "国际新闻内容"));
        newsProducer.sendNews("china.20190101",new News("凤凰TV","xxx企业荣登世界500强",new Date() , "国内新闻内容"));
    }
}
