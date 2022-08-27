package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CompanyDAO;

public class ListCompaniesAction implements Action {

    @Override
    public void execute(Context context) {
        CompanyDAO companyDAO = context.getCarSharingFactory().getCompanyDAO();
        var companies = companyDAO.getAllCompanies();
        System.out.println();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Company list:");
            companies.forEach(System.out::println);
        }
    }

    @Override
    public String toString() {
        return "Company list";
    }
}
