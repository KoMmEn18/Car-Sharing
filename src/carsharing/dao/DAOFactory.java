package carsharing.dao;

public abstract class DAOFactory {
    public abstract CompanyDAO getCompanyDAO();

    public static DAOFactory getDAOFactory(String dbName) {
        return new CarSharingDAOFactory(dbName);
    }
}
