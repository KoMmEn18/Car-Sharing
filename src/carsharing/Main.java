package carsharing;

public class Main {

    private static final String DEFAULT_DB_NAME = "carsharing";

    public static void main(String[] args) {
        CarSharing carSharing = new CarSharing(getDatabaseName(args));
        carSharing.run();
    }

    private static String getDatabaseName(String[] args) {
        String dbName = DEFAULT_DB_NAME;
        if (args.length == 2
                && args[0].equals("-databaseFileName")
                && !args[1].isEmpty()
                && !args[1].isBlank()) {
            dbName = args[1];
        }

        return dbName;
    }
}