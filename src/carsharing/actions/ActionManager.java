package carsharing.actions;

import carsharing.Context;
import carsharing.State;

public class ActionManager {
    private Action action;

    public void setAction(int action, State state) {
        this.action = state.getActions().getOrDefault(action, new UnknownAction());
    }

    public Action getAction() {
        return action;
    }

    public void execute(Context context) {
        this.action.execute(context);
    }
}
