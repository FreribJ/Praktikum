package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.Project;
import praktikum.Employees;
import praktikum.TextHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuProjectsEvaluation {
    public static void showProjects(Game game) {
        var projects = new ArrayList<Project>();
        var projectText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();

        for (Office office : game.getStudio().getOffices()) {
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
                extraInformation2D.add("Days left: " + calculateDaysLeft(project, game));
                int quantityOfDeveloper = 0;
                for(Developer developer : Employees.getEmployees(game.getStudio())){
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



    public static int calculateDaysLeft(Project project, Game game) {
        return project.getDeadline().differenceTo(game.getDay());
    }
}
