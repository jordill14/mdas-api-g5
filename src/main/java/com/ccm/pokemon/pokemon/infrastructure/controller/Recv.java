package com.ccm.pokemon.pokemon.infrastructure.controller;

import com.ccm.pokemon.pokemon.application.useCases.IncrementCountFavorites;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private final static String QUEUE_NAME = "pokemon";

    Channel channel;

    @Inject
    IncrementCountFavorites incrementCountFavorites;

    public Recv() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
            this.channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void main() throws Exception {

        this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            this.incrementCountFavorites.counterFavorite(Integer.parseInt(message));

            System.out.println(" [x] Received '" + message + "'");
        };
        this.channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
        });
    }
}