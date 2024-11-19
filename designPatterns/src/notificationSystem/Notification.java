package notificationSystem;

public class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void send() {
        System.out.println("Basic Notification: " + message);
    }
}