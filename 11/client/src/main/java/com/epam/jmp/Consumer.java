package com.epam.jmp;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer implements ExceptionListener {

	private static final String USER_NAME = "admin";
	private static final String PASSWORD = "admin";
	private static final String BROKER_URL = "tcp://localhost:61616";

	private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
			USER_NAME, PASSWORD, BROKER_URL);

	public static void main(String[] args) throws JMSException {
		new Consumer().consume(args[0]);
	}

	private void consume(String topicName) throws JMSException {
		Connection connection = connectionFactory.createConnection();
		connection.start();
		connection.setExceptionListener(this);
		Session session = connection.createSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createTopic(topicName);
		MessageConsumer consumer = session.createConsumer(destination);
		try {
			while (true) {
				Message message = consumer.receive();
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("Received: " + text);
				} else {
					System.out.println("Received: " + message);
				}
			}
		} finally {
			consumer.close();
			session.close();
			connection.close();
		}
	}

	// @Override
	public void onException(JMSException e) {
		System.out.println(e);
	}

}
