package com.qd.routing;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 *  消息生产者
 * todo  路由
 *  生产者产生消息，发送到交换机，交换机再与特定客户端的队列进行绑定
 * 订阅几乎一致，
 * 区别在于 交换机类型为 exchangeType=direct 交换机可以根据消息的 key 选择消息进入哪个队列，它比订
 * 阅发布更加细化。
 */
public class send {
    //队列名称
    private static  final String EXCHANGE_NAME="routing_exchange_direct";

    public static void main(String[] args) {

        try {
            //4.创建通道
            Channel channel = ConnectionUtil.getConn().createChannel();
            //TODO 5.申明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            //消息内容
            String message = "Hello World!";
            for (int i = 0; i < 20; i++) {
                channel.basicPublish(EXCHANGE_NAME,"u",null,(message+i).getBytes(StandardCharsets.UTF_8));
            }
            channel.basicPublish(EXCHANGE_NAME,"i",null,"你已经订阅".getBytes(StandardCharsets.UTF_8));
            System.out.println("传送完毕");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
