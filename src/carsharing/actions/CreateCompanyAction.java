package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CompanyDAO;

import java.util.Scanner;

public class CreateCompanyAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        CompanyDAO companyDAO = context.getCarSharingFactory().getCompanyDAO();
        System.out.println();
        System.out.println("Enter the company name:");
        String name = scanner.nextLine();
        while (name.isBlank() || name.isEmpty()) {
            System.out.println("Name is empty. Try again");
            name = scanner.nextLine();
        }
        if (companyDAO.insertCompany(name) > 0) {
            System.out.println("The company was created!");
        }
    }

    @Override
    public String toString() {
        return "Create a company";
    }
}
