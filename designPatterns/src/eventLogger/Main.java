package eventLogger;

public class Main {
    public static void main(String[] args) {
        EventLogger logger = EventLogger.getInstance();
        logger.configureOutput(true, true, "./designPatterns/src/eventLogger/test_log.log");

        logger.log(EventLogger.LogLevel.INFO, "This is an informational log.");
        logger.log(EventLogger.LogLevel.ERROR, "This is an error log.");
        logger.log(EventLogger.LogLevel.DEBUG, "This is a debug log.");

        System.out.println("Log History:");
        logger.getLogHistory().forEach(System.out::println);
    }
}
