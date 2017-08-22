package com.dmm.JMS;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer2 {
	private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
	private static final String BROKERURL = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		Connection connection = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKERURL);
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
			Queue queue = session.createQueue("FirstQueue1");
			MessageConsumer messageConsumer = session.createConsumer(queue);
			messageConsumer.setMessageListener(new JMSListener());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
