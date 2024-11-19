package eventLogger;

class ConsoleLogDestination implements LogDestination {
    @Override
    public void write(String logEntry) {
        System.out.println(logEntry);
    }
}
