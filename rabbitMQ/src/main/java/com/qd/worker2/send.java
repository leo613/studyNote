package com.qd.worker2;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 消息生产者
 * 公平派发
 */
public class send {
    //队列名称
    private static  final String QUEUE_NAME="worker2_queue";

    public static void main(String[] args) {

        try {
            //4.创建通道
            Channel channel = ConnectionUtil.getConn().createChannel();
            //5.申明队列
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //todo 6.有限制 消费者 消费信息(消费者确认后,一次重新消费1条信息)  能者多劳
            channel.basicQos(1);

            String message = "Hello World!";


            for (int i = 0; i < 20; i++) {
                channel.basicPublish("",QUEUE_NAME,null,(message+i).getBytes(StandardCharsets.UTF_8));
            }

            System.out.println("传送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
