package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CarDAO;
import carsharing.dao.CompanyDAO;
import carsharing.dao.CustomerDAO;
import carsharing.models.Car;
import carsharing.models.Company;
import carsharing.models.Customer;
import carsharing.util.ChooseUtil;

import java.util.Scanner;

public class RentCarAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        Customer customer = context.getCustomer();
        Car rentedCar = customer.getRentedCar();

        CompanyDAO companyDAO = context.getCarSharingFactory().getCompanyDAO();
        CarDAO carDAO = context.getCarSharingFactory().getCarDAO();
        CustomerDAO customerDAO = context.getCarSharingFactory().getCustomerDAO();

        var companies = companyDAO.getAllCompanies();
        System.out.println();
        if (rentedCar != null) {
            System.out.println("You've already rented a car!");
        } else if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            Company company = ChooseUtil.chooseCompany(scanner, companies);
            if (company != null) {
                var cars = carDAO.getAvailableCarsByCompany(company.getId());
                System.out.println();
                if (cars.isEmpty()) {
                    System.out.println("No available cars in the '" + company.getName() + "' company");
                } else {
                    Car car = ChooseUtil.chooseCar(scanner, cars);
                    if (car != null) {
                        customer.setRentedCar(car);
                        customerDAO.updateCustomer(customer);
                        System.out.println("You rented '" + car.getName() + "'");
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Rent a car";
    }
}
