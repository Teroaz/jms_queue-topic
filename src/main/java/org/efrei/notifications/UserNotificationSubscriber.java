package org.efrei.notifications;

import org.efrei.ActiveMQUtil;

import javax.jms.*;

public class UserNotificationSubscriber {
    public void subscribeToNewProducts(int id) throws JMSException {

        Connection connection = ActiveMQUtil.getConnection();

        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("NewProductsTopic");

        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message -> {
            TextMessage textMessage = (TextMessage) message;
            try {
                System.out.println("[Sub " + id + "] Notification d'un nouveau produit : " + textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });

    }
}