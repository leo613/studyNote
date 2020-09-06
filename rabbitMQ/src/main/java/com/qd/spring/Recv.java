package com.qd.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Recv {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:/spring/rabbit-context.xml");
        AmqpTemplate template = context.getBean(AmqpTemplate.class);
        String myqueue = (String) template.receiveAndConvert("myQueue");
        System.out.println(myqueue);
    }
}
