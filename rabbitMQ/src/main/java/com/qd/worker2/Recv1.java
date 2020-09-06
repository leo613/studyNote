package com.qd.worker2;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 消费者
 */
public class Recv1 {
    //队列名称
    private static  final String QUEUE_NAME="worker2_queue";

    public static void main(String[] args) throws IOException {

            //4.创建通道
           final Channel channel = ConnectionUtil.getConn().createChannel();
            //5.申明队列  durable:true 开启 持久化
            channel.queueDeclare(QUEUE_NAME,true,false,false,null);
            //todo 6.一次取一条信息
            channel.basicQos(1);

            //7.监听作用
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println("Recv1： " + message );
                //8.效率模拟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //9.反馈==>确认成功消费信息
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            };
        /**
         *
         * TODO 10.设置消费者 auotAck需要设置false
         * 模式1. 自动确认：只要消息从队列中获取,无论消费者获取到信息是否成功,都认为已经成功消费
         * 模式2. 手动确认: 消费者从队列中获取消息后,服务器会将该消息标记为不可用状态,等待消费信息的反馈,如果消费信息一直没有反馈,那么该消息一直处于不可用状态。
         */
        channel.basicConsume(QUEUE_NAME,false,deliverCallback,consumerTag -> {});

    }
}
