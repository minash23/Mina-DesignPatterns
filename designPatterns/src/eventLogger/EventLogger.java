package eventLogger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventLogger {
    private static volatile EventLogger instance;
    private final List<String> logHistory = new CopyOnWriteArrayList<>();
    private static final String DEFAULT_LOG_FILE = "application.log";

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    private EventLogger() {}

    public static EventLogger getInstance() {
        if (instance == null) {
            synchronized (EventLogger.class) {
                if (instance == null) {
                    instance = new EventLogger();
                }
            }
        }
        return instance;
    }

    public void log(LogLevel level, String message) {
        String logEntry = "[" + level + "] " + message;
        System.out.println(logEntry);
        logHistory.add(logEntry);
    }

    public List<String> getLogHistory() {
        return Collections.unmodifiableList(logHistory);
    }

    public void logToFile(LogLevel level, String message) {
        logToFile(level, message, DEFAULT_LOG_FILE);
    }

    public synchronized void logToFile(LogLevel level, String message, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            String logEntry = "[FILE] [" + level + "] " + message + "\n";
            writer.write(logEntry);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    public void archiveLogs() {
        archiveLogs(DEFAULT_LOG_FILE);
    }

    public void archiveLogs(String sourcePath) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String archivePath = "log_archive_" + timestamp + ".log";

        try {
            Files.copy(Paths.get(sourcePath), Paths.get(archivePath));
            // Optionally clear the original log file
            new FileWriter(sourcePath).close();
            log(LogLevel.INFO, "Logs archived to " + archivePath);
        } catch (IOException e) {
            log(LogLevel.ERROR, "Failed to archive logs: " + e.getMessage());
        }
    }
}