package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import praktikum.Employees;
import praktikum.TextHandler;

public class MenuDeveloperEvaluation {
    public static void showDeveloper(GameDevStudio studio) {
        String headline = "Number of Developer: " + Employees.getEmployees(studio).size();
        TextHandler.print(Employees.getEmployeesAsString(studio), headline, true);
    }
}
