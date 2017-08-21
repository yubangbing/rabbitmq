/**
 * TODO
 * 
 */
package com.aitongyi.rabbitmq.topic;

/**
 * @author hushuang
 *4 ͨ���ģʽ ��·��ģʽ����
 * ��·�ɼ���ĳģʽ����ƥ�䣬��ʱ������Ҫ����һ��ģʽ�ϣ���#��ƥ��һ���ʻ����ʣ���*��ֻƥ��һ���ʡ�
 */
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class TopicSend {

	private static final String EXCHANGE_NAME = "topic_logs";

	public static void main(String[] argv) {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");

			connection = factory.newConnection();
			channel = connection.createChannel();
//			����һ��ƥ��ģʽ�Ľ�����
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");

			// �����͵���Ϣ
			String[] routingKeys = new String[]{"quick.orange.rabbit", 
												"lazy.orange.elephant", 
												"quick.orange.fox", 
												"lazy.brown.fox", 
												"quick.brown.fox", 
												"quick.orange.male.rabbit", 
												"lazy.orange.male.rabbit"};
//			������Ϣ
	        for(String severity :routingKeys){
	        	String message = "From "+severity+" routingKey' s message!";
	        	channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
	        	System.out.println("TopicSend [x] Sent '" + severity + "':'" + message + "'");
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}
}