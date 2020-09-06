package com.qd.subscribe;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * 消费者
 */
public class Recv1 {
    //队列名称
    private static  final String EXCHANGE_NAME="ps_exchange_fanout";

    public static void main(String[] args) throws IOException {

            //4.创建通道
           final Channel channel = ConnectionUtil.getConn().createChannel();

            //todo 5.声明交换机  durable:true 开启 持久化 持久化意义在于，当无服务宕机恢复后，我们的消息也能随之恢复
            channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

            //todo 6.交换机和队列进行绑定
            String queue_name = channel.queueDeclare().getQueue();
             channel.queueBind(queue_name,EXCHANGE_NAME,"");

            //todo 7.一次取一条信息
            channel.basicQos(1);

            //8.监听作用
            DeliverCallback deliverCallback=(consumerTag, delivery) -> {
                String message=new String(delivery.getBody(),"UTF-8");
                System.out.println("Recv1： " + message );

                //9.效率模拟
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //10.确认成功消费信息
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
                }
            };
            //TODO 11.设置消费者 auotAck需要设置false
            channel.basicConsume(queue_name,false,deliverCallback,consumerTag -> {});

    }
}
