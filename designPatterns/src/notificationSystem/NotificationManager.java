package notificationSystem;

import java.util.HashMap;
import java.util.Map;

public class NotificationManager {
    private Map<String, NotificationStrategy> channels = new HashMap<>();
    private NotificationPreferences preferences = new NotificationPreferences();

    public void registerChannel(String channelName, NotificationStrategy strategy) {
        channels.put(channelName, strategy);
    }

    public void enableChannel(String channelName) {
        NotificationStrategy strategy = channels.get(channelName);
        if (strategy != null) {
            preferences.addNotificationChannel(strategy);
            System.out.println("Channel enabled: " + channelName);
        }
    }

    public void disableChannel(String channelName) {
        NotificationStrategy strategy = channels.get(channelName);
        if (strategy != null) {
            preferences.removeNotificationChannel(strategy);
            System.out.println("Channel disabled: " + channelName);
        }
    }

    public void sendNotification(String message) {
        preferences.sendNotification(message);
    }

    // Example usage
    public static void main(String[] args) {
        NotificationManager manager = new NotificationManager();

        // Register channels
        manager.registerChannel("SMS", new SMSNotificationStrategy());
        manager.registerChannel("Email", new EmailNotificationStrategy());
        manager.registerChannel("Slack", new SlackNotificationStrategy());

        // Enable specific channels
        manager.enableChannel("SMS");
        manager.enableChannel("Email");

        // Send notification
        manager.sendNotification("System update notification");

        // Disable a channel
        manager.disableChannel("SMS");
    }
}