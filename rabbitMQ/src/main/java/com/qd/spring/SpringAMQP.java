package com.qd.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.nio.charset.StandardCharsets;

public class SpringAMQP {


    public static void main(String[] args) throws InterruptedException {

//        AbstractApplicationContext context = new GenericXmlApplicationContext("classpath:/spring/rabbit-context.xml");
//        AmqpTemplate template = context.getBean(AmqpTemplate.class);
//        template.convertAndSend("myQueue", "你好！".getBytes(StandardCharsets.UTF_8));

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring/rabbit-context.xml");
        //RabbitMQ模板
        RabbitTemplate template = ctx.getBean(RabbitTemplate.class);
        //发送消息
        template.convertAndSend("Hello, world!");
        Thread.sleep(1000);
        ctx.destroy();
    }
}
