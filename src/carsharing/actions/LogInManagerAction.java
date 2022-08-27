package carsharing.actions;

import carsharing.Context;
import carsharing.State;

public class LogInManagerAction implements Action {
    @Override
    public void execute(Context context) {
        context.setCurrentState(State.MANAGER_MENU);
    }

    @Override
    public String toString() {
        return "Log in as a manager";
    }
}
