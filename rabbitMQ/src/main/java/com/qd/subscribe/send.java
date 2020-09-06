package com.qd.subscribe;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *  消息生产者
 * todo 发布订阅(接受者有需要就订阅)
 *  生产者产生消息，发送到交换机，交换机再与特定客户端的队列进行绑定
 *  交换机类型 exchangeType=fanout
 *  应用场景 比如一个消息又要发到短信,又要发到邮件.
 */
public class send {
    //队列名称
    private static  final String EXCHANGE_NAME="ps_exchange_fanout";

    public static void main(String[] args) {

        try {
            //4.创建通道
            Channel channel = ConnectionUtil.getConn().createChannel();
            //TODO 5.申明交换机
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

            //todo 6.有限制 消费者 消费信息(消费者确认后,一次重新消费1条信息)
            channel.basicQos(1);
            //消息内容
            String message = "Hello World!";
            for (int i = 0; i < 20; i++) {
                channel.basicPublish(EXCHANGE_NAME,"",null,(message+i).getBytes(StandardCharsets.UTF_8));
            }

            System.out.println("传送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
