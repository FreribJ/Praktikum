package praktikum;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

import java.util.ArrayList;

/**
 * Handles redundant operations with the {@link Developer}.
 */
public class Employees {

    /**
     * Returns an actual list of all hired {@link Developer} in all {@link Office}s.
     * @param studio {@link GameDevStudio} to lock at
     * @return a {@link ArrayList} of all employees
     */
    public static ArrayList<Developer> getEmployees(GameDevStudio studio){
        var employees = new ArrayList<Developer>();
        for (Office office : studio.getOffices()) {
            employees.addAll(office.getDevelopers());
        }
        return employees;
    }

    /**
     * Returns an actual list of all hired {@link Developer} names in all {@link Office}.
     * @param studio {@link GameDevStudio}
     * @return a {@link ArrayList} of all employee names
     */
    public static ArrayList<String> getEmployeesAsString(GameDevStudio studio){
        var employees = new ArrayList<String>();
        for (Office office : studio.getOffices()) {
            for (Developer developer : office.getDevelopers()) {
                employees.add(developer.getName().getName());
            }
        }
        return employees;
    }
}
