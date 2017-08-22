package com.dmm.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSProducer {
	
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	private static final int SEND_NUM = 10;
	
	public static void main(String[] args) {
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic("FirstTopic1");
			MessageProducer messageProducer = session.createProducer(topic);
			sendMessage(session, messageProducer);
			session.commit();
		} catch (JMSException e) {
			e.printStackTrace();
		}finally{
			if(connection!=null){
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	public static void sendMessage(Session session,MessageProducer messageProducer) throws JMSException{
		for(int i =0;i<SEND_NUM;i++){
			TextMessage textMessage = session.createTextMessage("ActiveMQ 发布的主题"+i);
			System.out.println("发送主题：ActiveMQ 发布的主题"+i);
			messageProducer.send(textMessage);
		}
	}
}
