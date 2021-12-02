package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.Project;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuProjectsEvaluation {
    public static void showProjects(Game game) {
        var projects = new ArrayList<String>();

        for (Office office : game.getStudio().getOffices()) {
            for (Developer developer : office.getDevelopers()) {
                if (developer.getWorkingOn() != null) {
                    projects.add("");
                    projects.add("Name: " + developer.getWorkingOn().getName().getName());
                    projects.add("Days left: " + calculateDaysLeft(developer.getWorkingOn(), game));
                }
            }

        }
        if(!projects.isEmpty()){
        TextHandler.print(projects, "current projects");
    } else{
        TextHandler.print("no current projects");
        }
    }

    public static int calculateDaysLeft(Project project, Game game){
        return project.getDeadline().differenceTo(game.getDay());
    }
}
