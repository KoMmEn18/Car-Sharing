package carsharing.actions;

import carsharing.Context;
import carsharing.models.Car;
import carsharing.models.Customer;

public class PrintRentedCarAction implements Action {

    @Override
    public void execute(Context context) {
        Customer customer = context.getCustomer();
        Car rentedCar = customer.getRentedCar();
        System.out.println();
        if (rentedCar != null) {
            System.out.println("Your rented car:");
            System.out.println(rentedCar.getName());
            System.out.println("Company:");
            System.out.println(rentedCar.getCompany().getName());
        } else {
            System.out.println("You didn't rent a car!");
        }
    }

    @Override
    public String toString() {
        return "My rented car";
    }
}
