package com.dmm.ActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSConsumer {
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
			Topic topic = session.createTopic("FirstTopic1");
			MessageConsumer messageConsumer = session.createConsumer(topic);
			messageConsumer.setMessageListener(new JMSListener());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
