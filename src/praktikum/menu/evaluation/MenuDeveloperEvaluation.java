package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import praktikum.Employees;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuDeveloperEvaluation {
    public static void showDeveloper(GameDevStudio studio) {
        String headline = "Number of Developer: " + Employees.getEmployees(studio).size();
        var extraInformation = new ArrayList<ArrayList<String>>();
        for(Developer developer : Employees.getEmployees(studio)){
            var extraInformation2D = new ArrayList<String>();
            extraInformation2D.add("Salary: " + developer.getSalary().toString());
            if(developer.getWorkingOn() != null)
                extraInformation2D.add("Working on project: " + developer.getWorkingOn().getName().getName());
            int totalSkill = developer.getSkills().getCoding() + developer.getSkills().getDesign() + developer.getSkills().getResearch() + developer.getSkills().getTesting();
            extraInformation2D.add("Total skill: " + totalSkill);
            extraInformation.add(extraInformation2D);
        }
        TextHandler.print(Employees.getEmployeesAsString(studio), headline, null, true, extraInformation);
    }
}
