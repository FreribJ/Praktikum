
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;
import gmbh.kdb.hsw.gdp.domain.Skillset;

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
        if (allProjects.size() == 0) {
            System.out.println("There are no Projects");
        } else {
            for (int i = 0; i < allProjects.size(); i++) {
                Project project = allProjects.get(i);
                System.out.println("Nr." + (i + 1) + " " + project); //Print Project
                if (allDeveloperWithoutProject.size() == 0) {
                    System.out.println("There are no available Developer");
                    allProjectsFastestDevelopers.add(new ArrayList<Developer>());
                } else {
                    printPossibleDevelopers(allDeveloperWithoutProject, project);

                    findFastestDevelopers(allDeveloperWithoutProject, project);

                    System.out.println();
                }
            }
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

    private void printPossibleDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        for (int j = 0; j < allDeveloperWithoutProject.size(); j++) {
            Developer dev = allDeveloperWithoutProject.get(j);
            try {
                System.out.print("Days to Finish: " + getDaysToFinishProject(dev, project) + " -> ");
            } catch (RuntimeException e) {
                System.out.print(e.getMessage() + " -> ");
            }
            System.out.println(dev);
        }
    }

    private void findFastestDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
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
            System.out.println("There is no Developer, who can do this project");
        } else {
            System.out.println("Best Developers: " + fastestForCurrentProject);
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
