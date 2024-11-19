package notificationSystem;

public class main {
    public static void main(String[] args) {
        // Story 1 & 5: Basic Notification System with Customizable Preferences
        System.out.println("=== Notification System Demonstration ===");

        // Create Notification Manager to handle channel preferences
        NotificationManager manager = new NotificationManager();

        // Story 6: Demonstrate Extensibility - Register Multiple Channels
        System.out.println("\n1. Registering Notification Channels:");
        manager.registerChannel("SMS", new SMSNotificationStrategy());
        manager.registerChannel("Email", new EmailNotificationStrategy());
        manager.registerChannel("Slack", new SlackNotificationStrategy());
        manager.registerChannel("WhatsApp", new WhatsAppNotificationStrategy());

        // Story 10: Enable/Disable Channels
        System.out.println("\n2. Enabling and Disabling Channels:");
        manager.enableChannel("SMS");
        manager.enableChannel("Email");
        manager.disableChannel("WhatsApp");

        // Story 7: Multiple Channel Notifications
        System.out.println("\n3. Multi-Channel Notification Service:");
        MultiChannelNotificationService multiChannelService = new MultiChannelNotificationService();
        multiChannelService.addChannel(new SMSNotificationStrategy());
        multiChannelService.addChannel(new EmailNotificationStrategy());

        // Send multi-channel notification
        multiChannelService.sendMultiChannelNotification("System update: Maintenance scheduled for tonight.");

        // Story 2, 3, 4: Specific Channel Notifications
        System.out.println("\n4. Individual Channel Notifications:");
        Notification systemUpdate = new Notification(
                "Critical system update requires immediate attention!",
                Notification.NotificationType.CRITICAL
        );

        // Send notification through specific channels
        NotificationStrategy smsChannel = new SMSNotificationStrategy();
        NotificationStrategy emailChannel = new EmailNotificationStrategy();
        NotificationStrategy slackChannel = new SlackNotificationStrategy();

        // Sending notification through individual channels
        System.out.println("SMS Channel Notification:");
        smsChannel.send(systemUpdate.getMessage());

        System.out.println("\nEmail Channel Notification:");
        emailChannel.send(systemUpdate.getMessage());

        System.out.println("\nSlack Channel Notification:");
        slackChannel.send(systemUpdate.getMessage());

        // Story 8: Demonstrate Extensibility with Decorators
        System.out.println("\n5. Notification Channel Extensibility:");
        NotificationStrategy pushSMSStrategy = new PushNotificationDecorator(
                new SMSNotificationStrategy()
        );
        pushSMSStrategy.send("Enhanced notification with push capabilities");

        // Story 9: Notification History Tracking
        System.out.println("\n6. Notification History:");
        NotificationHistory history = NotificationHistory.getInstance();

        // Log notifications across different channels
        history.addRecord(systemUpdate, "SMS");
        history.addRecord(systemUpdate, "Email");
        history.addRecord(systemUpdate, "Slack");

        // Print full notification history
        System.out.println("Notification History Log:");
        history.printHistory();

    }
}