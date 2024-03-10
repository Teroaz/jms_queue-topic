package org.efrei.notifications;

import org.efrei.ActiveMQUtil;

import javax.jms.*;

public class ProductNotificationPublisher {
    public void publishNewProduct(String productInfo) throws JMSException {

        Connection connection = ActiveMQUtil.getConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("NewProductsTopic");

        MessageProducer producer = session.createProducer(topic);
        TextMessage message = session.createTextMessage(productInfo);
        producer.send(message);

        System.out.println("Publication des infos d'un nouveau produit : " + productInfo);
    }
}
