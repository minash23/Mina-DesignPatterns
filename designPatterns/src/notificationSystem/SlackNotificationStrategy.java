package notificationSystem;

// Slack Notification Strategy
public class SlackNotificationStrategy implements NotificationStrategy {
    @Override
    public void send(String message) {
        System.out.println("Slack Notification: " + message);
    }
}