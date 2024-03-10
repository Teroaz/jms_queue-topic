# PhilRaphShop Information System Architecture Lab - TP1

## Overview

This repository contains the implementation for a lab work assignment (TP1) focused on redesigning the information
system architecture for PhilRaphShop, an online commerce platform. The primary objectives of this project are to
optimize order processing and enhance user communication about new products, maintenance operations, and promotions.

## Architecture Highlights

### Order Processing System (Queue)

We've introduced a queue-based system for order processing to assign each order to a specific preparation team,
eliminating overlaps and the need for inter-team coordination. This ensures that each command is processed in a FIFO (
First-In, First-Out) manner, improving reliability and predictability.

- **OrderProducer**: Places orders in the `OrderQueue` to be consumed by the preparation teams.
- **PreparationTeamConsumer**: Consumes and processes the orders from the `OrderQueue`.
- **Multithreading Implementation**: Ensures orders are dispatched and processed simultaneously by multiple consumers,
  enhancing efficiency.

### User Communication System (Topic)

To improve user experience through effective communication, a topic messaging system named `NewProductsTopic` has been
implemented.

- **ProductNotificationPublisher**: Publishes information about new products on the `NewProductsTopic`.
- **UserNotificationSubscriber**: Subscribes to the `NewProductsTopic` to receive notifications regarding new products.

### Directory Structure

- `src/main/java/org/efrei`
    - `notifications`
        - `NotificationsTopicImplementation`
        - `ProductNotificationPublisher`
        - `UserNotificationSubscriber`
    - `order`
        - `OrderProducer`
        - `OrderQueueImplementation`
        - `PreparationTeamConsumer` 
    - `ActiveMQUtil` (Utility class for ActiveMQ configuration and broker)

## Getting Started

Just start either NotificationsTopicImplementation or OrderQueueImplementation to start the system.