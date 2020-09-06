package com.qd.worker;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 消费者
 */
public class Recv1 {
    //队列名称
    private static  final String QUEUE_NAME="worker_queue";

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
                System.out.println("Recv1： " + message );

                //效率模拟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            //创建接受
            channel.basicConsume(QUEUE_NAME,deliverCallback,consumerTag -> {});

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
