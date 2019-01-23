package com.ocean.spring.consumer;

import com.ocean.spring.exchange.News;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Ocean on 2019/1/23 13:36.
 */
public class NewsConsumer {
    public void receive(News news){
        System.out.println("接收到来自"+news.getSource()+"的新闻"+news.getTitle()+":"+news.getContent()+".by "+news.getCreateTime());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }
}
