package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.*;
import praktikum.Employees;
import praktikum.exceptions.NotAvailableException;
import praktikum.special.SpecialProject;
import praktikum.TextHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles all {@link Project} related Operations.
 */
public class MenuProject {
    Game game;
    GameDevStudio studio;
    List<Project> allProjects;
    List<List<Developer>> allProjectsFastestDevelopers = new ArrayList<>();

    /**
     * Constructor of a {@link MenuProject}.
     * @param game the game operated with.
     */
    public MenuProject(Game game) {
        this.game = game;
        this.studio = this.game.getStudio();
        this.allProjects = this.studio.getProjectBoard().get();
    }

    /**
     * Prints all {@link Project} with each {@link Developer} and the day he needs to finish.
     * It also prints out the best {@link Developer} for the given {@link Project}.
     */
    public void showProjects() {
        ArrayList<Developer> allDeveloperWithoutProject = getAllDeveloperWithoutProject();
        var projectText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();
        if (allProjects.size() == 0) {
            TextHandler.print("There are no projects");
        } else {
            for (Project project : allProjects) {
                projectText.add("\"" + project.getName().getName() + "\"");
                var extraInformation2D = new ArrayList<String>();
                if (allDeveloperWithoutProject.size() == 0) {
                    extraInformation2D.add("There are no available Developers");
                    allProjectsFastestDevelopers.add(new ArrayList<>());
                } else {
                    extraInformation2D.add("Reward: " + project.getReward().getValue());
                    extraInformation2D.addAll(possibleDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add(findFastestDevelopers(allDeveloperWithoutProject, project));
                    extraInformation2D.add("");
                }
                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(projectText, "Projects", "Project", true, extraInformation);
        }
    }

    /**
     * Accepts a given {@link Project}.
     */
    public void accept() {
        try {
            if(allProjects == null || allProjects.isEmpty()){
                throw new NotAvailableException("You can not accept a project because there are no projects!");
            }
            int i = TextHandler.getInt("Which number?", 1, allProjects.size());
            i--;
            if (allProjectsFastestDevelopers.get(i).size() == 0) {
                throw new IllegalArgumentException("Can´t accept project because there is no capable developer!");
            }
            studio.acceptProject(allProjects.get(i), allProjectsFastestDevelopers.get(i));
            TextHandler.print("Project was accepted!");
            int finalI = i;
            studio.setProjectBoard(new ProjectBoard(studio.getProjectBoard().get().stream().filter(project -> project != allProjects.get(finalI)).toList()));
        } catch (IndexOutOfBoundsException e) {
            TextHandler.print("Wrong number!!! (You may try 'List' first)");
        } catch (IllegalArgumentException | NotAvailableException e) {
            TextHandler.print(e.getMessage());
        }
    }

    /**
     * creates new {@link Project} and adds it to the {@link ProjectBoard}.
     */
    public void create() {
        TextHandler.print("A project will be created. Please enter the following values:");
        var projectName = TextHandler.getText("Project name: ");

        var skillSet = TextHandler.getSkillset();

        var money = TextHandler.getDouble("Money: [value between: 8000 and 40000]", 8000, 40000);
        var customer = TextHandler.getText("Company name: ");
        var deadline = TextHandler.getInt("Days until the deadline: [value between 1 and 10]", 1, 10) + game.getDay().getNumber();

        var project = SpecialProject.createSpecialProject(projectName, skillSet, money, customer, deadline);

        List<Project> projectList = new ArrayList<>(studio.getProjectBoard().get());
        projectList.add(project);
        studio.setProjectBoard(new ProjectBoard(projectList));
        TextHandler.print("The project " + projectName + " has been created successfully!");
        game.log("New project "  +  projectName +  " was created.");
    }

    /**
     * Gives a List of all possible {@link Developer}s for a given {@link Project} in addition to the number of days it takes them to finish the project.
     * @param allDeveloperWithoutProject  {@link Developer} which don´t have a project yet.
     * @param project defines the given {@link Project}
     * @return List of {@link Developer}
     */
    private ArrayList<String> possibleDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        var extraInformation2D = new ArrayList<String>();
        for (Developer dev : allDeveloperWithoutProject) {
            String extraInformation2DText = "";
            try {
                extraInformation2DText = extraInformation2DText.concat("Days to Finish: " + getDaysToFinishProject(dev, project) + " -> ");
            } catch (NotAvailableException e) {
                extraInformation2DText = extraInformation2DText.concat(e.getMessage() + " -> ");
            }
            extraInformation2DText = extraInformation2DText.concat(dev.getName().getName());
            extraInformation2D.add(extraInformation2DText);
        }
        return extraInformation2D;
    }

    /**
     * Finds the fastest {@link Developer} for a given {@link Project}.
     * @param allDeveloperWithoutProject {@link Developer} to compare
     * @param project The given {@link Project}
     * @return {@link String} with the best {@link Developer}s
     */
    private String findFastestDevelopers(ArrayList<Developer> allDeveloperWithoutProject, Project project) {
        //Start der eventuellen While-Schleife für mehrere Developer(andere Strategie)
        ArrayList<Developer> fastestForCurrentProject = new ArrayList<>();
        int lowestDays = 1000;
        Developer fastest = null;
        for (Developer dev : allDeveloperWithoutProject) {
            try {
                int daysToFinishProject = this.getDaysToFinishProject(dev, project);
                if (daysToFinishProject < lowestDays) {
                    lowestDays = daysToFinishProject;
                    fastest = dev;
                }
            } catch (NotAvailableException ignored) {
            }
        }
        if (fastest != null) {
            fastestForCurrentProject.add(fastest);
        }
        //Ende der eventuellen While-Schleife für mehrere Developer

        allProjectsFastestDevelopers.add(fastestForCurrentProject);

        if (fastestForCurrentProject.size() == 0) {
             return "There is no developer, who can work on this project!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Developer dev: fastestForCurrentProject) {
                sb.append(dev.getName().getName()).append("     ");
            }
            return "Best developer(s): " + sb;
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
     * @param dev The {@link Developer}
     * @param pro The {@link Project}
     * @return Number of Days
     */
    private int getDaysToFinishProject(Developer dev, Project pro) throws NotAvailableException {
        Skillset effort = pro.getEffort();
        int days = 0;
        if (
                (dev.getSkills().getCoding() == 0 && effort.getCoding() != 0) ||
                        (dev.getSkills().getResearch() == 0 && effort.getResearch() != 0) ||
                        (dev.getSkills().getTesting() == 0 && effort.getTesting() != 0) ||
                        (dev.getSkills().getDesign() == 0 && effort.getDesign() != 0)
        ) {
            throw new NotAvailableException("Developer is unable to perform this project!");
        }
        while (effort.getCoding() != 0 || effort.getDesign() != 0 || effort.getResearch() != 0 || effort.getTesting() != 0) {
            days++;
            effort = effort.subtract(dev.getSkills());
        }
        return days;
    }

    /**
     * Same as {@link #getDaysToFinishProject(Developer, Project)} just with a List of {@link Developer}s.
     * @return Number of Days
     */
    private int getDaysToFinishProject(List<Developer> devs, Project pro) throws NotAvailableException {
        Skillset effort = pro.getEffort();
        int days = 0;
        for(Developer dev: devs) {
            if (
                    (dev.getSkills().getCoding() == 0 && effort.getCoding() != 0) ||
                            (dev.getSkills().getResearch() == 0 && effort.getResearch() != 0) ||
                            (dev.getSkills().getTesting() == 0 && effort.getTesting() != 0) ||
                            (dev.getSkills().getDesign() == 0 && effort.getDesign() != 0)
            ) {
                throw new NotAvailableException("Developer is unable to perform this project!");
            }
            while (effort.getCoding() != 0 || effort.getDesign() != 0 || effort.getResearch() != 0 || effort.getTesting() != 0) {
                days++;
                effort = effort.subtract(dev.getSkills());
            }
        }
        return days;
    }

}
