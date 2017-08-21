/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.routing;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * @author hushuang
 *3 路由模式
 * 1、 发送消息到交换机并且要指定路由key
2、 消费者将队列绑定到交换机时需要指定路由key
 */
public class RoutingSendDirect {

    private static final String EXCHANGE_NAME = "direct_logs";
 // 路由关键字
 	private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};
 	
    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
//		声明交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
//		发送消息
        for(String severity :routingKeys){
        	String message = "Send the message level:" + severity;
        	channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
        	System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
        channel.close();
        connection.close();
    }
}