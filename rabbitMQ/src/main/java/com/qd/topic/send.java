package com.qd.topic;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *  消息生产者
 * todo 主题
 *  生产者产生消息，发送到交换机，交换机再与特定客户端的队列进行绑定
 * 和路由几乎一致，
 * 区别在于 路由类型为 exchangeType=topic key 可以使用统配符 # 表示一个或多个 * 表示一个
 */
public class send {
    //队列名称
    private static  final String EXCHANGE_NAME="topic_exchange";

    public static void main(String[] args) {

        try {
            //4.创建通道
            Channel channel = ConnectionUtil.getConn().createChannel();
            //TODO 5.申明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

            //消息内容
            String message = "Hello World!";

            channel.basicPublish(EXCHANGE_NAME,"m.qq",null,message.getBytes(StandardCharsets.UTF_8));
            System.out.println("传送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
