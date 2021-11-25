import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;

import java.util.ArrayList;
import java.util.List;

public class MenuDeveloper {
    public static void showDeveloper(GameDevStudio studio) {
        String headline = "Number of Developer: " + Employees.getEmployees(studio).size();
        TextHandler.print(Employees.getEmployeesAsString(studio), headline, true);
    }
}
