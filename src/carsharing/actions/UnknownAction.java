package carsharing.actions;

import carsharing.Context;

public class UnknownAction implements Action {

    @Override
    public void execute(Context context) {
        System.out.println("Undefined action. Please try again");
    }
}
