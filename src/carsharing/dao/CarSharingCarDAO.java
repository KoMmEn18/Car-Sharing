package carsharing.dao;

import carsharing.models.Car;

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
            SELECT * 
            FROM `car`
            WHERE `company_id` = ?
            ORDER BY `id`
        """;

        List<Car> cars = new ArrayList<>();

        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, companyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    cars.add(new Car(id, name));
                }
            }

        } catch (SQLException e) {
            System.out.println("ERROR > Could not get cars");
            System.out.println(e.getMessage());
        }

        return cars;
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
}
