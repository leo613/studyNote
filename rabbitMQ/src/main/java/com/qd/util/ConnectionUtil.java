package com.qd.util;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConn(){
        //建立连接
        //连接工厂
        ConnectionFactory factory=new ConnectionFactory();
        //2.设置工厂属性  地址
        factory.setHost("localhost");
        factory.setUsername("taotao");
        factory.setPassword("taotao");
        factory.setVirtualHost("/taotao");
        Connection conn = null;
        try {
            // 3.获得连接实例
            conn = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
