package notificationSystem;

public class Notification {
    private String message;
    private NotificationType type;

    public enum NotificationType {
        ALERT, INFO, WARNING, CRITICAL
    }

    public Notification(String message, NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }
}