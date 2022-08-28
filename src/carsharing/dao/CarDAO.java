package carsharing.dao;

import carsharing.models.Car;

import java.util.List;

public interface CarDAO {
    public List<Car> getCarsByCompany(int companyId);
    public int insertCar(String name, int companyId);
}
