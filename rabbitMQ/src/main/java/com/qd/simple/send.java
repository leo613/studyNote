package com.qd.simple;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 消息生产者
 */
public class send {
    //队列名称
    private static  final String QUEUE_NAME="simple_queue";

    public static void main(String[] args) {

        try {
            //4.创建通道
            Channel channel = ConnectionUtil.getConn().createChannel();
            //5.申明队列( durable:声明是否持久化;  durable: 是否是独占的queue; autoDelete:不使用时是否自动删除; arguments:其他参数)
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            String message = "Hello World!";

            for (int i = 0; i < 10; i++) {
                channel.basicPublish("",QUEUE_NAME,null,(message+i).getBytes(StandardCharsets.UTF_8));
            }

            System.out.println(" [x] Sent '" + message + "'");
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
