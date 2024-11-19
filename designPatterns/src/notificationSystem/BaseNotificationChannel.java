package notificationSystem;

public abstract class BaseNotificationChannel implements NotificationChannel {
    protected String channelName;

    public BaseNotificationChannel(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public abstract void send(Notification notification);

    protected void logNotification(Notification notification) {
        System.out.printf("[%s] %s: %s\n",
                channelName,
                notification.getType(),
                notification.getMessage());
    }
}