/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.publish;

/**
 * @author hushuang
 * 2 ����ģʽ
 *һ�������߷��͵���Ϣ�ᱻ��������߻�ȡ��
�����ߣ����Խ���Ϣ���͵����л����ǽ�������
�����ߣ�ֻ�ܴӶ����л�ȡ��Ϣ��
�����Ϣ���͵�û�ж��а󶨵Ľ������ϣ���ô��Ϣ����ʧ��
 *
 *
 *
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

//		�ַ���Ϣ
		for(int i = 0 ; i < 5; i++){
			String message = "Hello World! " + i;
			 channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		     System.out.println(" [x] Sent '" + message + "'");
		}
        channel.close();
        connection.close();
    }
}
