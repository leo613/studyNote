package com.qd.routing;

import com.qd.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 消费者
 */
public class Recv2 {
    //队列名称
    private static  final String EXCHANGE_NAME="routing_exchange_direct";

    public static void main(String[] args) throws Exception {
        //4.创建通道
        final Channel channel = ConnectionUtil.getConn().createChannel();

        //todo 5.声明交换机  durable:true 开启 持久化 持久化意义在于，当无服务宕机恢复后，我们的消息也能随之恢复
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT );

        //todo 6.交换机和队列进行绑定
        String queue_name = channel.queueDeclare().getQueue();
        channel.queueBind(queue_name,EXCHANGE_NAME,"u");

        //监听作用
        DeliverCallback deliverCallback=(consumerTag, delivery) -> {
            String message=new String(delivery.getBody(),"UTF-8");
            System.out.println("Recv1： " + message );

            //效率模拟
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        //TODO 设置消费者 auotAck需要设置false
        channel.basicConsume(queue_name,false,deliverCallback,consumerTag -> {});
    }
}
