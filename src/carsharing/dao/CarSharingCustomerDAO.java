package carsharing.dao;

import carsharing.models.Car;
import carsharing.models.Company;
import carsharing.models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarSharingCustomerDAO implements CustomerDAO {

    @Override
    public List<Customer> getAllCustomers() {
        String query = """
            SELECT 
                cu.`id`, cu.`name`, cu.`rented_car_id`, ca.`name` as rented_car_name, co.`id` as company_id, 
                co.`name` as company_name
            FROM `customer` cu
            LEFT JOIN `car` ca
                ON cu.`rented_car_id` = ca.`id`
            LEFT JOIN `company` co
                ON ca.`company_id` = co.`id`
            ORDER BY cu.`id`
        """;

        List<Customer> customers = new ArrayList<>();

        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int rentedCarId = resultSet.getInt("rented_car_id");
                String rentedCarName = resultSet.getString("rented_car_name");
                if (resultSet.wasNull()) {
                    customers.add(new Customer(id, name));
                } else {
                    int companyId = resultSet.getInt("company_id");
                    String companyName = resultSet.getString("company_name");
                    Company company = new Company(companyId, companyName);
                    Car car = new Car(rentedCarId, rentedCarName, company);
                    customers.add(new Customer(id, name, car));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR > Could not get customers");
        }

        return customers;
    }

    @Override
    public int insertCustomer(String name) {
        String query = """
            INSERT INTO customer(`name`)
            VALUES(?)
        """;
        int index = -1;
        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR > Could not insert customer");
        }

        return index;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String query = """
            UPDATE customer
            SET rented_car_id = ?
            WHERE id = ?
        """;

        boolean updated = false;
        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            Car car = customer.getRentedCar();
            if (car != null) {
                statement.setInt(1, customer.getRentedCar().getId());
            } else {
                statement.setNull(1, Types.NULL);
            }

            statement.setInt(2, customer.getId());
            updated = statement.execute();
        } catch (SQLException e) {
            System.out.println("ERROR > Could not update customer");
        }

        return updated;
    }
}
