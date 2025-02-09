package com.precluch.stocknotifier.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public void sendAlert(String userId, String message) {
        // TODO: Implement actual notification logic (email, SMS, etc.)
        // For now, just log to console
        System.out.printf("Sending notification to %s: %s%n", userId, message);

        // Example email integration (pseudo-code):
        // emailClient.send(
        //     getEmailForUser(userId),
        //     "Stock Price Alert",
        //     message
        // );
    }
}
