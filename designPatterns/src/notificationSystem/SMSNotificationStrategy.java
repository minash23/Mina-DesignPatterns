package notificationSystem;

// SMS Notification Strategy
public class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
        // In a real implementation, this would integrate with an SMS gateway
    }
}