package notificationSystem;

// Abstract Decorator for future extensibility
public abstract class NotificationDecorator implements NotificationStrategy {
    protected NotificationStrategy wrappedStrategy;

    public NotificationDecorator(NotificationStrategy strategy) {
        this.wrappedStrategy = strategy;
    }

    @Override
    public void send(String message) {
        wrappedStrategy.send(message);
    }
}
