package org.efrei;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

import javax.jms.Connection;
import javax.jms.JMSException;
import java.net.URI;

public class ActiveMQUtil {

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
            try {
                connection.start();
            } catch (JMSException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }

    private static Connection createConnection() {
        BrokerService broker;
        try {
            broker = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
            broker.start();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        try {
            return connectionFactory.createConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
