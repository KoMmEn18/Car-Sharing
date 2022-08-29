package carsharing.dao;

import carsharing.models.Car;
import carsharing.models.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarSharingCarDAO implements CarDAO {

    @Override
    public List<Car> getCarsByCompany(int companyId) {
        String query = """
            SELECT ca.`id`, ca.`name`, ca.`company_id`, co.`name` as company_name
            FROM `car` ca
            INNER JOIN `company` co
                ON ca.`company_id` = co.`id`
            WHERE `company_id` = ?
            ORDER BY `id`
        """;

        return this.getCarListByQuery(companyId, query);
    }


    public List<Car> getAvailableCarsByCompany(int companyId) {
        String query = """
            SELECT ca.`id`, ca.`name`, ca.`company_id`, co.`name` as company_name
            FROM `car` ca
            INNER JOIN `company` co
                ON ca.`company_id` = co.`id`
            LEFT JOIN `customer` cu
                ON ca.`id` = cu.`rented_car_id`
            WHERE `company_id` = ? AND `rented_car_id` IS NULL
            ORDER BY `id`
        """;

        return this.getCarListByQuery(companyId, query);
    }

    @Override
    public int insertCar(String name, int companyId) {
        String query = """
            INSERT INTO car(`name`, `company_id`)
            VALUES(?, ?)
        """;
        int index = -1;
        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setInt(2, companyId);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR > Could not insert car");
        }

        return index;
    }

    private List<Car> getCarListByQuery(int companyId, String query) {
        List<Car> cars = new ArrayList<>();

        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, companyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String companyName = resultSet.getString("company_name");
                    Company company = new Company(companyId, companyName);
                    cars.add(new Car(id, name, company));
                }
            }
        } catch (SQLException e) {
            System.out.println("ERROR > Could not get cars");
            System.out.println(e.getMessage());
        }

        return cars;
    }
}
