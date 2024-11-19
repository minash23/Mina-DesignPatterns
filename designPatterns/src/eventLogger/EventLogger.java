package eventLogger;

public class EventLogger {
    private static EventLogger instance;

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private EventLogger() {}

    public static synchronized EventLogger getInstance() {
        if (instance == null) {
            instance = new EventLogger();
        }
        return instance;
    }
    public void log(LogLevel level, String message) {
        // Basic log method
        System.out.println("[" + level + "] " + message);
    }
}