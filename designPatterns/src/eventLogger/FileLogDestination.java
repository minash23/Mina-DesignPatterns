package eventLogger;

import java.io.FileWriter;
import java.io.IOException;

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
