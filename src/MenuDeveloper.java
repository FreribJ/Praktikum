import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;

import java.util.ArrayList;
import java.util.List;

public class MenuDeveloper {
    public static void showDeveloper(GameDevStudio studio) {
        System.out.println("Number of Developer: " + Employees.getEmployees(studio).size());
        for (int i = 0; i < Employees.getEmployees(studio).size(); i++) {
            System.out.println((i + 1) + ". " + Employees.getEmployees(studio).get(i));
        }
    }
}
