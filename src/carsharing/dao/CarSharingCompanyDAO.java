package carsharing.dao;

import carsharing.models.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarSharingCompanyDAO implements CompanyDAO {

    @Override
    public List<Company> getAllCompanies() {
        String query = """
            SELECT * 
            FROM `company`
            ORDER BY `id`
        """;

        List<Company> companies = new ArrayList<>();

        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                companies.add(new Company(id, name));
            }
        } catch (SQLException e) {
            System.out.println("ERROR > Could not get companies");
        }

        return companies;
    }

    @Override
    public int insertCompany(String name) {
        String query = """
            INSERT INTO company(`name`)
            VALUES(?)
        """;
        int index = -1;
        try (Connection connection = CarSharingDAOFactory.createConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            index = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERROR > Could not insert company");
        }

        return index;
    }
}
