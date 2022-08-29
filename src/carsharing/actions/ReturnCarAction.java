package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CustomerDAO;
import carsharing.models.Car;
import carsharing.models.Customer;

public class ReturnCarAction implements Action {

    @Override
    public void execute(Context context) {
        Customer customer = context.getCustomer();
        Car rentedCar = customer.getRentedCar();
        CustomerDAO customerDAO = context.getCarSharingFactory().getCustomerDAO();
        System.out.println();
        if (rentedCar != null) {
            customer.setRentedCar(null);
            customerDAO.updateCustomer(customer);
            System.out.println("You've returned a rented car!");
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    @Override
    public String toString() {
        return "Return a rented car";
    }
}
