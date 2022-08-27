package carsharing.actions;

import carsharing.Context;

public class ExitAction implements Action {
    @Override
    public void execute(Context context) {
        System.exit(1);
    }

    @Override
    public String toString() {
        return "Exit";
    }
}
