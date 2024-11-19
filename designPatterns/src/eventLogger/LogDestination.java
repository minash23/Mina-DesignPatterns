package eventLogger;

interface LogDestination {
    void write(String logEntry);
}
