package carsharing;

import carsharing.dao.DAOFactory;
import carsharing.models.Company;

import java.util.Scanner;

public class Context {
    private static final Scanner scanner = new Scanner(System.in);
    private static Context context = null;
    private static DAOFactory carSharingFactory;
    private State state = State.MAIN_MENU;
    private Company company = null;

    private Context(String dbName) {
        carSharingFactory = DAOFactory.getDAOFactory(dbName);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public DAOFactory getCarSharingFactory() {
        return carSharingFactory;
    }

    public State getCurrentState() {
        return state;
    }

    public void setCurrentState(State state) {
        this.state = state;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static Context getContext(String dbName) {
        if (context == null) {
            context = new Context(dbName);
        }

        return context;
    }
}
