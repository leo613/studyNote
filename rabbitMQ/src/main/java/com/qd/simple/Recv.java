package com.qd.simple;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Recv {
    //队列名称
    private static  final String QUEUE_NAME="simple_queue";

    public static void main(String[] args) {

        Channel channel = null;
        try {
            //4.创建通道
            channel = ConnectionUtil.getConn().createChannel();
            //5.申明队列  durable:true 开启 持久化
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //监听作用
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            //创建接受
            channel.basicConsume(QUEUE_NAME,deliverCallback,consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
