package org.efrei.order;

import org.efrei.ActiveMQUtil;

import javax.jms.*;

public class OrderProducer {

    public void produceOrder() throws JMSException {
        // Connexion à ActiveMQ via la classe ActiveMQUtil
        Connection connection = ActiveMQUtil.getConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("OrderQueue");
        MessageProducer producer = session.createProducer(queue);

        String text = "Commande de l'utilisateur XYZ, contenant : 1x produit1, 2x produit2";
        TextMessage message = session.createTextMessage(text);

        producer.send(message);
        System.out.println("Message envoyé : " + text);

        session.close();
    }
}
