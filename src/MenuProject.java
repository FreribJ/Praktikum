
import gmbh.kdb.hsw.gdp.domain.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handels all {@link Project} related Operations.
 */
public class MenuProject {
    GameDevStudio studio;
    List<Project> allProjects;
    List<List<Developer>> allProjectsFastestDevelopers = new ArrayList<>();

    public MenuProject(GameDevStudio studio) {
        this.studio = studio;
        this.allProjects = this.studio.getProjectBoard().get();
    }

    /**
     * Prints all {@link Project} with each {@link Developer} and the Day he needs to finish.
     * It also prints out the best {@link Developer} for the given {@link Project}.
     */
    public void showProjects() {
        ArrayList<Developer> allDeveloperWithoutProject = getAllDeveloperWithoutProject();
        var projectText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();
        if (allProjects.size() == 0) { //Überprüft ob
            TextHandler.print("There are no projects");
        } else {
            for (int i = 0; i < allProjects.size(); i++) { //Iterate throw all Projects
                Project project = allProjects.get(i);
                projectText.add(project.getName().getName());
                var extraInformation2D = new ArrayList<String>();
                if (allDeveloperWithoutProject.size() == 0) {
                    extraInformation2D.add("There are no available Developers");
                    allProjectsFastestDevelopers.add(new ArrayList<Developer>());
                } else {
                    extraInformation2D.addAll(possibleDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add(findFastestDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add("");
                }
                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(projectText, "Projects", null, true, extraInformation);
        }
    }

    /**
     * Accepts a given {@link Project}
     * @param i shows which Project will be accepted.
     */
    public void accept(int i) {
        try {
            i--;
            if (allProjectsFastestDevelopers.get(i).size() == 0) {
                throw new IllegalArgumentException("Can´t accept project, because there is no capable Developer!");
            }
            studio.acceptProject(allProjects.get(i), allProjectsFastestDevelopers.get(i));
            TextHandler.print("Project was accepted!");

        } catch (IndexOutOfBoundsException e) {
            TextHandler.print("Wrong Number!!! (You may try 'List' first)");
        } catch (IllegalArgumentException e) {
            TextHandler.print(e.getMessage());
        } //catch (Exception e) {
        //    System.out.println(e.getMessage());
        //}
    }

    /**
     * creates new {@link Project} and adds it to the {@link ProjectBoard}
     */
    public void create() {
        var projectName = TextHandler.getText("Projektname: ");
        var skillsAsStringArray = TextHandler.getText("Skills [separated with comma]: ").split(",");
        var skillSet = new Skillset(Integer.parseInt(skillsAsStringArray[0]), Integer.parseInt(skillsAsStringArray[1]), Integer.parseInt(skillsAsStringArray[2]), Integer.parseInt(skillsAsStringArray[3]));
        var money = Double.parseDouble(TextHandler.getText("Money: "));
        var customer = TextHandler.getText("Company Name: ");
        var deadline = Integer.parseInt(TextHandler.getText("Deadline: "));

        var project = SpecialProject.createSpecialProject(projectName, skillSet, money, customer, deadline);

        List<Project> projectList = new ArrayList<>(studio.getProjectBoard().get());
        projectList.add(project);
        studio.setProjectBoard(new ProjectBoard(projectList));
    }

    /**
     * Gives a List of all possible Developers for a given Project
     * @param allDeveloperWithoutProject  {@link Developer} which don´t have a project yet.
     * @param project defines the given {@link Project}
     * @return List of {@link Developer}
     */
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
            extraInformation2DText = extraInformation2DText.concat(dev.getName().getName());
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
            StringBuilder sb = new StringBuilder();
            for (Developer dev: fastestForCurrentProject) {
                sb.append(dev.getName().getName() + "     ");
            }
            return "Best Developer(s): " + sb;
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

    private int getDaysToFinishProject(List<Developer> devs, Project pro) {
        Skillset effort = pro.getEffort();
        int days = 0;
        for(Developer dev: devs) {
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
        }
        return days;
    }

}
