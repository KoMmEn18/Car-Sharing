package carsharing.actions;

import carsharing.Context;
import carsharing.State;

public class BackAction implements Action {
    @Override
    public void execute(Context context) {
        context.setCurrentState(State.MAIN_MENU);
    }

    @Override
    public String toString() {
        return "Back";
    }
}
