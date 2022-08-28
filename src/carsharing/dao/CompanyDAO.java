package carsharing.dao;

import carsharing.models.Company;

import java.util.List;

public interface CompanyDAO {
    public List<Company> getAllCompanies();
    public int insertCompany(String name);
}
