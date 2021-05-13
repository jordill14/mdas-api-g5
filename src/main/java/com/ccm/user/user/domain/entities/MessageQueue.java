package com.ccm.user.user.domain.entities;

public class MessageQueue {

    public MessageQueue(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    String message;
}
