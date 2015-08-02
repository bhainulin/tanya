package com.epam.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Producer extends HttpServlet {

    private static final String USER_NAME = "admin";
    private static final String PASSWORD = "admin";
    private static final String BROKER_URL = "tcp://localhost:61616";

    private ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topic = req.getParameter("topic");
        String message = req.getParameter("message");
        System.out.println("Sending message: {" + topic + " ; " + message + "}");
        try {
            send(topic, message);
            req.setAttribute("respCode", 200);
            req.setAttribute("respMess", "ok");
        } catch (Exception e) {
            System.out.println(e);
            req.setAttribute("respCode", 500);
            req.setAttribute("respMess", e.getMessage());
        }
        req.setAttribute("last", topic);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    private void send(String topic, String messageText) throws JMSException {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        try {
            Destination destination = session.createTopic(topic);
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            TextMessage message = session.createTextMessage(messageText);
            producer.send(message);
        } finally {
            session.close();
            connection.close();
        }
        System.out.println("Message sent.");
    }

}

