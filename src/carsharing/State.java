package carsharing;

import carsharing.actions.*;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public enum State {
    MAIN_MENU(getMainMenuActions()),
    MANAGER_MENU(getManagerMenuActions());

    private final Map<Integer, Action> actions;

    State(Map<Integer, Action> actions) {
        this.actions = actions;
    }

    public Map<Integer, Action> getActions() {
        return actions;
    }

    private static Map<Integer, Action> getMainMenuActions() {
        var map = new LinkedHashMap<Integer, Action>();
        map.put(1, new LogInManagerAction());
        map.put(0, new ExitAction());

        return Collections.unmodifiableMap(map);
    }

    private static Map<Integer, Action> getManagerMenuActions() {
        var map = new LinkedHashMap<Integer, Action>();
        map.put(1, new ListCompaniesAction());
        map.put(2, new CreateCompanyAction());
        map.put(0, new BackAction());

        return Collections.unmodifiableMap(map);
    }
}
