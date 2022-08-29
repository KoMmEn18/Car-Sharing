package carsharing.models;

public class Car extends Entity {
    private Company company;

    public Car(int id, String name, Company company) {
        super(id, name);
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }
}
