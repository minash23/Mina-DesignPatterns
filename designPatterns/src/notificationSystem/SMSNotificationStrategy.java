package notificationSystem;

// SMS Notification Strategy
public class SMSNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("SMS Notification: " + message);
    }
}