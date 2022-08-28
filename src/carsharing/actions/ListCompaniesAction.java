package carsharing.actions;

import carsharing.Context;
import carsharing.State;
import carsharing.dao.CompanyDAO;

import java.util.Scanner;

public class ListCompaniesAction implements Action {

    @Override
    public void execute(Context context) {
        Scanner scanner = context.getScanner();
        CompanyDAO companyDAO = context.getCarSharingFactory().getCompanyDAO();
        var companies = companyDAO.getAllCompanies();
        System.out.println();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Choose the company:");
            for (int i = 0; i < companies.size(); i++) {
                System.out.println(i + 1 + ". " + companies.get(i).getName());
            }
            System.out.println("0. Back");
            int index = -1;
            boolean indexValid = false;
            while (!indexValid) {
                try {
                    index = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException ignore) {}

                if (index > 0 && index <= companies.size()) {
                    indexValid = true;
                    context.setCompany(companies.get(index - 1));
                    context.setCurrentState(State.COMPANY_MENU);
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
        return "Company list";
    }
}
