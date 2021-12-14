package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.Project;
import praktikum.Employees;
import praktikum.TextHandler;

import java.util.ArrayList;

/**
 * Handles the evaluation of the {@link Project}.
 */
public class MenuProjectsEvaluation {
    Game game;

    /**
     * Constructs a {@link MenuProjectsEvaluation}.
     * @param game the game operated with
     */
    public MenuProjectsEvaluation(Game game) {
        this.game = game;
    }

    /**
     * Prints all the accepted {@link Project} with the name, remaining days for the {@link Project} and the developer working on.
     */
    public void showProjects() {
        var projects = new ArrayList<Project>();
        var projectText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();

        for (Office office : this.game.getStudio().getOffices()) {
            for (Developer developer : office.getDevelopers()) {
                if (developer.getWorkingOn() != null) {
                    projects.add(developer.getWorkingOn());
                }
            }
        }

        if (projects.size() == 0) {
            TextHandler.print("There are no current projects");
        } else {
            for (Project project : projects) {
                projectText.add("\"" + project.getName().getName() + "\"");
                var extraInformation2D = new ArrayList<String>();
                extraInformation2D.add("Days left: " + this.calculateDaysLeft(project));
                int quantityOfDeveloper = 0;
                for (Developer developer : Employees.getEmployees(this.game.getStudio())) {
                    if (developer.getWorkingOn() != null && developer.getWorkingOn().equals(project))
                        quantityOfDeveloper++;
                }
                extraInformation2D.add("Developers: " + quantityOfDeveloper);
                extraInformation2D.add("");

                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(projectText, "Projects", "Project", true, extraInformation);
        }

    }

    /**
     * Calculates the remaining days for a {@link Project}.
     * @param project the {@link Project} calculated with
     * @return the remaining days
     */
    private int calculateDaysLeft(Project project) {
        return project.getDeadline().differenceTo(this.game.getDay());
    }
}
