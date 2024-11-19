package notificationSystem;

public class NotificationService {
    private NotificationStrategy strategy;

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public void sendNotification(String message) {
        if (strategy != null) {
            strategy.send(message);
        } else {
            System.out.println("Basic Notification: " + message);
        }
    }
}