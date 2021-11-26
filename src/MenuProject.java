
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;
import gmbh.kdb.hsw.gdp.domain.Skillset;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MenuProject {
    GameDevStudio studio;
    List<Project> allProjects;
    List<List<Developer>> allProjectsFastestDevelopers = new ArrayList<>();

    public MenuProject(GameDevStudio studio) {
        this.studio = studio;
        this.allProjects = this.studio.getProjectBoard().get();
    }

    public void showProjects() {
        ArrayList<Developer> allDeveloperWithoutProject = getAllDeveloperWithoutProject();
        var projectText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();
        if (allProjects.size() == 0) {
            TextHandler.print("There are no projects");
        } else {
            for (int i = 0; i < allProjects.size(); i++) {
                Project project = allProjects.get(i);
                projectText.add(project.toString());
                var extraInformation2D = new ArrayList<String>();
                if (allDeveloperWithoutProject.size() == 0) {
                    extraInformation2D.add("There are no available Developer");
                    allProjectsFastestDevelopers.add(new ArrayList<Developer>());
                } else {
                    extraInformation2D.addAll(possibleDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add(findFastestDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add("");
                }
                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(projectText, null, null, true, extraInformation);
        }
    }

    public void accept(int i) {
        try {
            i--;
            if (allProjectsFastestDevelopers.get(i).size() == 0) {
                throw new Exception("Can´t accept project, because there is no capable Developer!");
            }
            studio.acceptProject(allProjects.get(i), allProjectsFastestDevelopers.get(i));
            System.out.println("Project was accepted!");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Wrong Number!!! (You may try 'List' first)");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private ArrayList<String> possibleDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        var extraInformation2D = new ArrayList<String>();
        for (int j = 0; j < allDeveloperWithoutProject.size(); j++) {
            Developer dev = allDeveloperWithoutProject.get(j);
            String extraInformation2DText = "";
            try {
                extraInformation2DText = extraInformation2DText.concat("Days to Finish: " + getDaysToFinishProject(dev, project) + " -> ");
            } catch (RuntimeException e) {
                extraInformation2DText = extraInformation2DText.concat(e.getMessage() + " -> ");
            }
            extraInformation2DText = extraInformation2DText.concat(dev.toString());
            extraInformation2D.add(extraInformation2DText);
        }
        return extraInformation2D;
    }

    private String findFastestDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        ArrayList<Developer> fastestForCurrentProject = new ArrayList<>();
        //while (true) {
        int lowestDays = 100;
        Developer fastest = null;
        for (int j = 0; j < allDeveloperWithoutProject.size(); j++) {
            Developer dev = allDeveloperWithoutProject.get(j);
            try {
                int daysToFinishProject = this.getDaysToFinishProject(dev, project);
                if (daysToFinishProject < lowestDays) {
                    lowestDays = daysToFinishProject;
                    fastest = dev;
                }
            } catch (RuntimeException e) {
            }
        }
        fastestForCurrentProject.add(fastest);
        //}
        allProjectsFastestDevelopers.add(fastestForCurrentProject);

        if (fastestForCurrentProject.size() == 0) {
             return "There is no Developer, who can do this project";
        } else {
            return "Best Developer: " + fastestForCurrentProject.toString();
        }
    }

    private ArrayList<Developer> getAllDeveloperWithoutProject() {
        var allDeveloperWithoutProject = new ArrayList<Developer>();
        for (var dev : Employees.getEmployees(this.studio)) {
            if (dev.getWorkingOn() == null) {
                allDeveloperWithoutProject.add(dev);
            }
        }
        return allDeveloperWithoutProject;
    }

    private int getDaysToFinishProject(Developer dev, Project pro) {
        Skillset effort = pro.getEffort();
        int days = 0;
        if (
                (dev.getSkills().getCoding() == 0 && effort.getCoding() != 0) ||
                        (dev.getSkills().getResearch() == 0 && effort.getResearch() != 0) ||
                        (dev.getSkills().getTesting() == 0 && effort.getTesting() != 0) ||
                        (dev.getSkills().getDesign() == 0 && effort.getDesign() != 0)
        ) {
            throw new RuntimeException("Developer is unable to perform this project!");
        }
        while (effort.getCoding() != 0 || effort.getDesign() != 0 || effort.getResearch() != 0 || effort.getTesting() != 0) {
            days++;
            effort = effort.subtract(dev.getSkills());
        }
        return days;
    }

}
