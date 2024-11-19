package notificationSystem;

import java.util.ArrayList;
import java.util.List;

public class MultiChannelNotificationService {
    private List<NotificationStrategy> channels = new ArrayList<>();

    public void addChannel(NotificationStrategy channel) {
        channels.add(channel);
    }

    public void sendMultiChannelNotification(String message) {
        for (NotificationStrategy channel : channels) {
            channel.send(message);
        }
    }
}