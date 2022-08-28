package carsharing.actions;

import carsharing.Context;
import carsharing.dao.CarDAO;

public class ListCarsAction implements Action {

    @Override
    public void execute(Context context) {
        CarDAO carDAO = context.getCarSharingFactory().getCarDAO();
        int companyId = context.getCompany().getId();
        var cars = carDAO.getCarsByCompany(companyId);
        System.out.println();
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            for (int i = 0; i < cars.size(); i++) {
                System.out.println(i + 1 + ". " + cars.get(i).getName());
            }
        }
    }

    @Override
    public String toString() {
        return "Car list";
    }
}
