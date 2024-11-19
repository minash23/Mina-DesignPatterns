package notificationSystem;

import notificationSystem.NotificationStrategy;

// Example of an extensible channel (WhatsApp)
public class WhatsAppNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("WhatsApp Notification: " + message);
        // Placeholder for WhatsApp integration
    }
}