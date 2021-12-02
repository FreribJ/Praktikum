package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.Project;
import praktikum.Employees;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuProjectsEvaluation {
    Game game;

    public MenuProjectsEvaluation(Game game) {
        this.game = game;
    }

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

        if (projects.size() == 0) { //Überprüft ob
            TextHandler.print("There are no current projects");
        } else {
            for (int i = 0; i < projects.size(); i++) { //Iterate throw all Projects
                Project project = projects.get(i);
                projectText.add("\"" + project.getName().getName() + "\"");
                var extraInformation2D = new ArrayList<String>();
                extraInformation2D.add("Days left: " + this.calculateDaysLeft(project));
                int quantityOfDeveloper = 0;
                for(Developer developer : Employees.getEmployees(this.game.getStudio())){
                    if(developer.getWorkingOn() != null && developer.getWorkingOn().equals(project))
                        quantityOfDeveloper++;
                }
                extraInformation2D.add("Developers: " + quantityOfDeveloper);
                extraInformation2D.add("");

                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(projectText, "Projects", "Project", true, extraInformation);
        }

    }

    public int calculateDaysLeft(Project project) {
        return project.getDeadline().differenceTo(this.game.getDay());
    }
}
