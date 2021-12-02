package praktikum.menu;

import gmbh.kdb.hsw.gdp.domain.*;
import praktikum.Employees;
import praktikum.special.SpecialProject;
import praktikum.TextHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all {@link Project} related Operations.
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
            int finalI = i;
            studio.setProjectBoard(new ProjectBoard(studio.getProjectBoard().get().stream().filter(project -> project != allProjects.get(finalI)).toList()));
        } catch (IndexOutOfBoundsException e) {
            TextHandler.print("Wrong Number!!! (You may try 'List' first)");
        } catch (IllegalArgumentException e) {
            TextHandler.print(e.getMessage());
        }
    }

    /**
     * creates new {@link Project} and adds it to the {@link ProjectBoard}
     */
    public void create() {
        TextHandler.print("A project will be created. Please enter the following values:");
        var projectName = TextHandler.getText("Projektname: ");

        var coding = TextHandler.getInt("Coding skill [0 - 10]: ");
        var research = TextHandler.getInt("Research skill [0 - 10]: ");
        var testing = TextHandler.getInt("Testing skill [0 - 10]: ");
        var design = TextHandler.getInt("Design skill[0 - 10]: ");
        var skillSet = new Skillset(coding, research, testing, design);

        var money = TextHandler.getDouble("Money: ");
        var customer = TextHandler.getText("Company name: ");
        var deadline = Integer.parseInt(TextHandler.getText("Deadline: "));

        var project = SpecialProject.createSpecialProject(projectName, skillSet, money, customer, deadline);

        List<Project> projectList = new ArrayList<>(studio.getProjectBoard().get());
        projectList.add(project);
        studio.setProjectBoard(new ProjectBoard(projectList));
        TextHandler.print("The project " + projectName + " has been created successfully!");
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

    /**
     * Finds the fastest {@link Developer} for a given {@link Project}.
     * @param allDeveloperWithoutProject {@link Developer} to Compare.
     * @param project The given {@link Project} .
     * @return Returns a String with the best {@link Developer}s.
     */
    private String findFastestDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        ArrayList<Developer> fastestForCurrentProject = new ArrayList<>();
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

    /**
     * Gives a List of the all {@link Developer} how are not assigned to an {@link Project} yet.
     * @return List of {@link Developer}s
     */
    private ArrayList<Developer> getAllDeveloperWithoutProject() {
        var allDeveloperWithoutProject = new ArrayList<Developer>();
        for (var dev : Employees.getEmployees(this.studio)) {
            if (dev.getWorkingOn() == null) {
                allDeveloperWithoutProject.add(dev);
            }
        }
        return allDeveloperWithoutProject;
    }

    /**
     * Gives the amount of days a given {@link Developer} needs to finish a given {@link Project}
     * @param dev The {@link Developer}.
     * @param pro The {@link Project}.
     * @return Number of Days
     */
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

    /**
     * Same as {@link #getDaysToFinishProject(Developer, Project)} just with a List of {@link Developer}s.
     */
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
