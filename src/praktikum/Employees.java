package praktikum;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    public static ArrayList<Developer> getEmployees(GameDevStudio studio){
        var employees = new ArrayList<Developer>();
        for (Office office : studio.getOffices()) {
            for (Developer developer : office.getDevelopers()) {
                employees.add(developer);
            }
        }
        return employees;
    }

    public static ArrayList<String> getEmployeesAsString(GameDevStudio studio){
        var employees = new ArrayList<String>();
        for (Office office : studio.getOffices()) {
            for (Developer developer : office.getDevelopers()) {
                employees.add(developer.toString());
            }
        }
        return employees;
    }
}
