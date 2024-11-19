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

interface LogDestination {
    void write(String logEntry);
}

class ConsoleLogDestination implements LogDestination {
    @Override
    public void write(String logEntry) {
        System.out.println(logEntry);
    }
}

class FileLogDestination implements LogDestination {
    private String filePath;

    public FileLogDestination(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void write(String logEntry) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(logEntry + "\n");
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }
}

public class EventLogger {
    private static volatile EventLogger instance;
    private final List<String> logHistory = new CopyOnWriteArrayList<>();
    private static final String DEFAULT_LOG_FILE = "application.log";

    private boolean consoleEnabled = true;
    private boolean fileEnabled = false;
    private String currentLogFile = DEFAULT_LOG_FILE;
    private List<LogDestination> destinations = new ArrayList<>();

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

    public void configureOutput(boolean consoleOutput, boolean fileOutput) {
        this.consoleEnabled = consoleOutput;
        this.fileEnabled = fileOutput;
    }

    public void configureOutput(boolean consoleOutput, boolean fileOutput, String logFilePath) {
        configureOutput(consoleOutput, fileOutput);
        this.currentLogFile = logFilePath;
    }

    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private String formatLogEntry(LocalDateTime timestamp, LogLevel level, String message) {
        return String.format("[%s] [%s] %s",
                timestamp.format(TIMESTAMP_FORMAT),
                level.name().toUpperCase(),
                message);
    }

    public void log(LogLevel level, String message) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logEntry = formatLogEntry(timestamp, level, message);

        if (consoleEnabled) {
            System.out.println(logEntry);
        }

        if (fileEnabled) {
            try (FileWriter writer = new FileWriter(currentLogFile, true)) {
                writer.write(logEntry + "\n");
            } catch (IOException e) {
                System.err.println("Failed to write to log file: " + e.getMessage());
            }
        }

        if (!destinations.isEmpty()) {
            for (LogDestination destination : destinations) {
                destination.write(logEntry);
            }
        }

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

    public void addDestination(LogDestination destination) {
        destinations.add(destination);
    }

    public void removeDestination(LogDestination destination) {
        destinations.remove(destination);
    }
}