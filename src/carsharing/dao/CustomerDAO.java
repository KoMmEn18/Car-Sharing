package carsharing.dao;

import carsharing.models.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getAllCustomers();
    public int insertCustomer(String name);
    public boolean updateCustomer(Customer customer);
}
