package carsharing.util;

import carsharing.models.Car;
import carsharing.models.Company;
import carsharing.models.Entity;

import java.util.List;
import java.util.Scanner;

public class ChooseUtil {

    public static Company chooseCompany(Scanner scanner, List<Company> companies) {
        System.out.println("Choose a company:");
        return (Company) chooseEntity(scanner, companies);
    }

    public static Car chooseCar(Scanner scanner, List<Car> cars) {
        System.out.println("Choose a car:");
        return (Car) chooseEntity(scanner, cars);
    }

    private static Entity chooseEntity(Scanner scanner, List<? extends Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            System.out.println(i + 1 + ". " + entities.get(i).getName());
        }
        System.out.println("0. Back");
        int index = -1;
        while (true) {
            try {
                index = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException ignore) {}

            if (index > 0 && index <= entities.size()) {
                return entities.get(index - 1);
            } else if (index == 0) {
                return null;
            }

            System.out.println("Index invalid. Try again");
        }
    }
}
