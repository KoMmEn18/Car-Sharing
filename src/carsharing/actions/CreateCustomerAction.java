package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CustomerDAO;

import java.util.Scanner;

public class CreateCustomerAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        CustomerDAO customerDAO = context.getCarSharingFactory().getCustomerDAO();
        System.out.println();
        System.out.println("Enter the customer name:");
        String name = scanner.nextLine();
        while (name.isBlank() || name.isEmpty()) {
            System.out.println("Name is empty. Try again");
            name = scanner.nextLine();
        }
        if (customerDAO.insertCustomer(name) > 0) {
            System.out.println("The customer was added!");
        }
    }

    @Override
    public String toString() {
        return "Create a customer";
    }
}
