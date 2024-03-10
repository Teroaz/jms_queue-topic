package org.efrei.notifications;

import org.efrei.ActiveMQUtil;

public class NotificationsTopicImplementation {

    public static void main(String[] args) {

        ActiveMQUtil.getConnection();

        final ProductNotificationPublisher productNotificationPublisher = new ProductNotificationPublisher();
        final UserNotificationSubscriber userNotificationSubscriber1 = new UserNotificationSubscriber();
        final UserNotificationSubscriber userNotificationSubscriber2 = new UserNotificationSubscriber();
        final UserNotificationSubscriber userNotificationSubscriber3 = new UserNotificationSubscriber();
        final UserNotificationSubscriber userNotificationSubscriber4 = new UserNotificationSubscriber();
        final UserNotificationSubscriber userNotificationSubscriber5 = new UserNotificationSubscriber();

        // Exécution de la publication des nouveaux produits dans son propre thread
        new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    productNotificationPublisher.publishNewProduct("Nouveau produit " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Exécution de la souscription aux nouveaux produits dans son propre thread
        new Thread(() -> {
            try {
                userNotificationSubscriber1.subscribeToNewProducts(1);
                userNotificationSubscriber2.subscribeToNewProducts(2);
                userNotificationSubscriber3.subscribeToNewProducts(3);
                userNotificationSubscriber4.subscribeToNewProducts(4);
                userNotificationSubscriber5.subscribeToNewProducts(5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
