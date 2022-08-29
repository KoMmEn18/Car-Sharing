package carsharing.dao;

public abstract class DAOFactory {
    public abstract CompanyDAO getCompanyDAO();
    public abstract CarDAO getCarDAO();
    public abstract CustomerDAO getCustomerDAO();

    public static DAOFactory getDAOFactory(String dbName) {
        return new CarSharingDAOFactory(dbName);
    }
}
