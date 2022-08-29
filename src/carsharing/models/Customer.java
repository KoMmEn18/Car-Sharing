package carsharing.models;

public class Customer extends Entity {
    private Car rentedCar;

    public Customer(int id, String name) {
        super(id, name);
        this.rentedCar = null;
    }

    public Customer(int id, String name, Car rentedCar) {
        this(id, name);
        this.rentedCar = rentedCar;
    }

    public Car getRentedCar() {
        return rentedCar;
    }

    public void setRentedCar(Car rentedCar) {
        this.rentedCar = rentedCar;
    }
}
