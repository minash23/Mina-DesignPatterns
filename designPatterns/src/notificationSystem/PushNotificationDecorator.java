package notificationSystem;

// Example of a decorator that could add extra functionality
public class PushNotificationDecorator extends NotificationDecorator {
    public PushNotificationDecorator(NotificationStrategy strategy) {
        super(strategy);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Push Notification: " + message);
    }
}
