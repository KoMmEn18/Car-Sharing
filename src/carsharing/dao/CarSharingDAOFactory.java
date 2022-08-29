package carsharing.dao;

import carsharing.models.Customer;

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

    @Override
    public CarDAO getCarDAO() {
        return new CarSharingCarDAO();
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        return new CarSharingCustomerDAO();
    }

    private void createTables() {
        String query = """
            CREATE TABLE IF NOT EXISTS `company` (
                `id` INT PRIMARY KEY AUTO_INCREMENT,
                `name` VARCHAR(255) UNIQUE NOT NULL
            );
            
            CREATE TABLE IF NOT EXISTS `car` (
                `id` INT PRIMARY KEY AUTO_INCREMENT,
                `name` VARCHAR(255) UNIQUE NOT NULL,
                `company_id` INT NOT NULL,
                CONSTRAINT company_fk
                FOREIGN KEY (company_id) 
                REFERENCES company(id)
            );
            
            CREATE TABLE IF NOT EXISTS `customer` (
                `id` INT PRIMARY KEY AUTO_INCREMENT,
                `name` VARCHAR(255) UNIQUE NOT NULL,
                `rented_car_id` INT DEFAULT NULL,
                CONSTRAINT rented_car_fk
                FOREIGN KEY (rented_car_id)
                REFERENCES car(id)
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
