package carsharing.actions;

import carsharing.Context;
import carsharing.State;
import carsharing.dao.CompanyDAO;
import carsharing.models.Company;
import carsharing.util.ChooseUtil;

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
            Company company = ChooseUtil.chooseCompany(scanner, companies);
            if (company != null) {
                context.setCompany(company);
                context.setCurrentState(State.COMPANY_MENU);
            }
        }
    }

    @Override
    public String toString() {
        return "Company list";
    }
}
