package org.efrei.order;

import org.efrei.ActiveMQUtil;

public class OrderQueueImplementation {

    public static void main(String[] args) {

        ActiveMQUtil.getConnection();

        final OrderProducer orderProducer = new OrderProducer();
        final PreparationTeamConsumer preparationTeamConsumer1 = new PreparationTeamConsumer();
        final PreparationTeamConsumer preparationTeamConsumer2 = new PreparationTeamConsumer();

        // Exécution de la production des commandes dans son propre thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    orderProducer.produceOrder();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Exécution des consommateurs dans leurs propres threads
        new Thread(() -> {
            try {
                preparationTeamConsumer1.consumeOrder(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                preparationTeamConsumer2.consumeOrder(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
