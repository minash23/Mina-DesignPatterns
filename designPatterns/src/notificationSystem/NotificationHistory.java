package notificationSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationHistory {
    private static NotificationHistory instance;
    private List<NotificationRecord> records = new ArrayList<>();

    private static class NotificationRecord {
        LocalDateTime timestamp;
        Notification notification;
        String channel;

        NotificationRecord(Notification notification, String channel) {
            this.timestamp = LocalDateTime.now();
            this.notification = notification;
            this.channel = channel;
        }

        @Override
        public String toString() {
            return String.format("[%s] Channel: %s, Type: %s, Message: %s",
                    timestamp, channel,
                    notification.getType(),
                    notification.getMessage());
        }
    }

    private NotificationHistory() {}

    public static synchronized NotificationHistory getInstance() {
        if (instance == null) {
            instance = new NotificationHistory();
        }
        return instance;
    }

    public void addRecord(Notification notification, String channel) {
        records.add(new NotificationRecord(notification, channel));
    }

    public List<NotificationRecord> getHistory() {
        return new ArrayList<>(records);
    }

    public void printHistory() {
        records.forEach(System.out::println);
    }
}