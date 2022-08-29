package carsharing.actions;

import carsharing.Context;
import carsharing.State;
import carsharing.dao.CustomerDAO;

import java.util.Scanner;

public class LogInCustomerAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        CustomerDAO companyDAO = context.getCarSharingFactory().getCustomerDAO();
        var customers = companyDAO.getAllCustomers();
        System.out.println();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            for (int i = 0; i < customers.size(); i++) {
                System.out.println(i + 1 + ". " + customers.get(i).getName());
            }
            System.out.println("0. Back");
            int index = -1;
            boolean indexValid = false;
            while (!indexValid) {
                try {
                    index = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException ignore) {}

                if (index > 0 && index <= customers.size()) {
                    indexValid = true;
                    context.setCustomer(customers.get(index - 1));
                    context.setCurrentState(State.CUSTOMER_MENU);
                } else if (index == 0) {
                    indexValid = true;
                } else {
                    System.out.println("Index invalid. Try again");
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Log in as a customer";
    }
}
