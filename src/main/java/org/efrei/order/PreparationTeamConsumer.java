package org.efrei.order;

import org.efrei.ActiveMQUtil;

import javax.jms.*;

public class PreparationTeamConsumer {

    public void consumeOrder(int id) throws JMSException {
        // Connexion à ActiveMQ via la classe ActiveMQUtil
        Connection connection = ActiveMQUtil.getConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("OrderQueue");
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            if (message instanceof TextMessage textMessage) {
                try {
                    System.out.println("[Team " + id + "] Commande reçue  : " + textMessage.getText());
                    Thread.sleep(1000);
                } catch (JMSException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }
}
