import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    Game gameInstance;
    boolean letGameContinue = false;

    public void run() {
        gameInstance = Game.create(iGameHandler -> {
            printRelevantInformation();
            return letGameContinue;
        });

        gameInstance.start();
        menuStructure();
    }

    //Main-Menu
    public void menuStructure() {
        try {
            switch (TextHandler.getText("Whats your next choice? [continue; evaluation; applications; projects; continueAll; end]: ")) {
                case "end":
                    //Terminate program
                    System.out.println("end");
                    break;
                case "continueall":
                    System.out.println("continues");
                    letGameContinue = true;
                    gameInstance.start();
                    break;
                case "evaluation":
                    this.menuEvaluationStructure();
                    System.out.println();
                    menuStructure();
                    break;
                case "applications":
                    this.menuApplicationStructure();
                    System.out.println();
                    menuStructure();
                    break;
                case "projects":
                    this.menuProjectsStructure(null);
                    System.out.println();
                    menuStructure();
                    break;
                case "continue":
                    gameInstance.start();
                    menuStructure();
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            menuStructure();
        }
    }

    //Sub-Menus
    public void menuEvaluationStructure() {
        try {
            switch (TextHandler.getText("Which Evaluation? [log; offices; developer]: ")) {
                case "log":
                    MenuEventLog.showEventLog(gameInstance);
                    break;
                case "offices":
                    MenuOffices.showOffices(gameInstance.getStudio());
                    break;
                case "developer":
                    MenuDeveloper.showDeveloper(gameInstance.getStudio());
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            menuApplicationStructure();
        }
    }

    public void menuApplicationStructure() {
        MenuApplication.showApplicationDevelopers(gameInstance.getStudio());
        System.out.println();
        try {
        switch (TextHandler.getText("Do you want to hire one? [yes; no]: ")) {
            case "yes":
                MenuApplication.hireApplicationDeveloper(gameInstance.getStudio());
                break;
            case "no":
                System.out.println("No Developer hired");
                break;
            default:
                throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e){
            System.out.println(e.getMessage());
            menuApplicationStructure();
        }
    }

    public void menuProjectsStructure(MenuProject menuProject) {
        try {
            switch (TextHandler.getText("What do you want to do? [list; accept; back]")) {
                case "list":
                    menuProject = new MenuProject(gameInstance.getStudio());
                    menuProject.showProjects();
                    menuProjectsStructure(menuProject);
                    break;
                case "accept":
                    if (menuProject == null) {
                        throw new RuntimeException("You need to use List first");
                    }
                    menuProject.accept(Integer.parseInt(TextHandler.getText("Which number?")));
                    menuProjectsStructure(menuProject);
                    break;
                case "back":
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            menuProjectsStructure(menuProject);
        }
        catch (NumberFormatException e) {
            System.out.println("You have to give a number!");
            menuProjectsStructure(menuProject);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            menuProjectsStructure(menuProject);
        }

    }

    //Helping Methods
    public void printRelevantInformation() {
        System.out.println("Relevant information: ");
        System.out.println("Companyname: " + gameInstance.getStudio().getName().getName());
        System.out.println("Cash: " + gameInstance.getStudio().getCash().toString());
        System.out.println("Number of Offices: " + gameInstance.getStudio().getOffices().size());
        System.out.println();
    }
}

