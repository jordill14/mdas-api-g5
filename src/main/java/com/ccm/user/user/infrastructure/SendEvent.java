package com.ccm.user.user.infrastructure;

import com.ccm.user.user.domain.entities.MessageQueue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

@ApplicationScoped
@Named("send")
public class SendEvent {

    private final static String QUEUE_NAME = "pokemon";

    Channel channel;

    public SendEvent() {
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

    public void sendMessage(MessageQueue messageQueue) {
        try {
            this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = messageQueue.getMessage();
            this.channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
