package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CarDAO;

import java.util.Scanner;

public class CreateCarAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        CarDAO carDAO = context.getCarSharingFactory().getCarDAO();
        System.out.println();
        System.out.println("Enter the car name:");
        String name = scanner.nextLine();
        while (name.isBlank() || name.isEmpty()) {
            System.out.println("Name is empty. Try again");
            name = scanner.nextLine();
        }
        if (carDAO.insertCar(name, context.getCompany().getId()) > 0) {
            System.out.println("The car was added!");
        }
    }

    @Override
    public String toString() {
        return "Create a car";
    }
}
