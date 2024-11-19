package eventLogger;

import java.util.ArrayList;
import java.util.List;

public class EventLogger {
    private static EventLogger instance;
    private List<String> logHistory = new ArrayList<>();

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
        String logEntry = "[" + level + "] " + message;
        System.out.println(logEntry);
        logHistory.add(logEntry);
    }

    public List<String> getLogHistory() {
        return new ArrayList<>(logHistory);
    }

    public void logToConsole(LogLevel level, String message) {
        System.out.println("[CONSOLE] [" + level + "] " + message);
    }
}