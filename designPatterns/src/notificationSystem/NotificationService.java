package notificationSystem;

public class NotificationService {
    public void sendNotification(String message) {
        Notification notification = new Notification(message);
        notification.send();
    }
}
