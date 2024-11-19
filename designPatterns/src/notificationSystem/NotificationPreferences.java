package notificationSystem;

import java.util.ArrayList;
import java.util.List;

public class NotificationPreferences {
    private List<NotificationStrategy> activeStrategies = new ArrayList<>();

    public void addNotificationChannel(NotificationStrategy strategy) {
        activeStrategies.add(strategy);
    }

    public void removeNotificationChannel(NotificationStrategy strategy) {
        activeStrategies.remove(strategy);
    }

    public void sendNotification(String message) {
        for (NotificationStrategy strategy : activeStrategies) {
            strategy.send(message);
        }
    }
}
