package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;
import praktikum.Employees;
import praktikum.TextHandler;

import java.util.ArrayList;

/**
 * Handles the evaluation of the developer.
 */
public class MenuDeveloperEvaluation {
    GameDevStudio studio;

    /**
     * Constructs a {@link MenuDeveloperEvaluation}.
     * @param studio the studio operated with.
     */
    public MenuDeveloperEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    /**
     * Prints all the hired developer with name, salary, total skill and possible {@link Project} the developer is working on.
     */
    public void showDeveloper() {
        String headline = "These are the developers";
        var extraInformation = new ArrayList<ArrayList<String>>();
        for(Developer developer : Employees.getEmployees(this.studio)){
            var extraInformation2D = new ArrayList<String>();
            extraInformation2D.add("Salary: " + developer.getSalary().toString());
            if(developer.getWorkingOn() != null)
                extraInformation2D.add("Working on project: " + developer.getWorkingOn().getName().getName());
            int totalSkill = developer.getSkills().getCoding() + developer.getSkills().getDesign() + developer.getSkills().getResearch() + developer.getSkills().getTesting();
            extraInformation2D.add("Total skill: " + totalSkill);
            extraInformation.add(extraInformation2D);
        }
        TextHandler.print(Employees.getEmployeesAsString(this.studio), headline, null, true, extraInformation);
    }
}
