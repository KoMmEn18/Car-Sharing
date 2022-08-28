package carsharing.actions;

import carsharing.Context;
import carsharing.State;

public class BackAction implements Action {
    @Override
    public void execute(Context context) {
        State state = context.getCurrentState();
        State newState = switch (state) {
            case MAIN_MENU, MANAGER_MENU -> State.MAIN_MENU;
            case COMPANY_MENU -> State.MANAGER_MENU;
        };
        context.setCurrentState(newState);
    }

    @Override
    public String toString() {
        return "Back";
    }
}
