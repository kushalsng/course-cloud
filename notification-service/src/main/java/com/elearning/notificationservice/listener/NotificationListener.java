package com.elearning.notificationservice.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @RabbitListener(queues = "activityQueue")
    public void handleActivityEvent(String message) {
        System.out.println("Received Notification Event: " + message);
        // Simulate sending Email/SMS
        sendNotification(message);
    }

    private void sendNotification(String message) {
        System.out.println("Sending Email/SMS for: " + message);
    }
}
