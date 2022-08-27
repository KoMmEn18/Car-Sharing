package carsharing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CarSharingDAOFactory extends DAOFactory {

    private static final String DRIVER = "org.h2.Driver";
    private static String DBURL = "jdbc:h2:./src/carsharing/db/";

    public CarSharingDAOFactory(String dbName) {
        DBURL += dbName;
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR > Database driver not found");
        }
        createTables();
    }

    @Override
    public CompanyDAO getCompanyDAO() {
        return new CarSharingCompanyDAO();
    }

    private void createTables() {
        String query = """
            CREATE TABLE IF NOT EXISTS `company` (
                `id` INT PRIMARY KEY AUTO_INCREMENT,
                `name` VARCHAR(255) UNIQUE NOT NULL
            );
        """;

        try (Connection connection = createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.execute();
        } catch (SQLException e) {
            System.out.println("ERROR > Could not create tables");
        }
    }

    public static Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DBURL);
        connection.setAutoCommit(true);

        return connection;
    }
}
