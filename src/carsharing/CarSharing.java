package carsharing;

import carsharing.actions.ActionManager;

public class CarSharing {

    private final ActionManager actionManager = new ActionManager();
    private final Context context;

    public CarSharing(String dbName) {
        context = Context.getContext(dbName);
    }

    public void run() {
        while (true) {
            printActionMenu();
            int action = -1;
            try {
                action = Integer.parseInt(context.getScanner().nextLine());
            } catch (NumberFormatException ignore) {}

            actionManager.setAction(action, context.getCurrentState());
            actionManager.execute(context);
            System.out.println();
        }
    }

    private void printActionMenu() {
        printMenuHeader();
        printMenuBody();
    }

    private void printMenuHeader() {
        if (context.getCurrentState() == State.COMPANY_MENU) {
            System.out.println("'" + context.getCompany().getName() + "' company");
        }
    }

    private void printMenuBody() {
        context.getCurrentState()
                .getActions()
                .forEach((key, value) -> System.out.println(key + ". " + value));
    }
}
