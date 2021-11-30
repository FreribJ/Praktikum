import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;
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
                    TextHandler.print("end");
                    break;
                case "continueall":
                    TextHandler.print("continues");
                    letGameContinue = true;
                    gameInstance.start();
                    break;
                case "evaluation":
                    this.menuEvaluationStructure();
                    menuStructure();
                    break;
                case "applications":
                    this.menuApplicationStructure();
                    menuStructure();
                    break;
                case "projects":
                    this.menuProjectsStructure(null);
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
                case "projects":

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
        try {
        switch (TextHandler.getText("Do you want to hire one? [yes; no]: ")) {
            case "yes":
                MenuApplication.hireApplicationDeveloper(gameInstance.getStudio());
                break;
            case "no":
                TextHandler.print("No Developer hired");
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
        var relevantInformation = new ArrayList<String>();
        relevantInformation.add("Company Name: " + gameInstance.getStudio().getName().getName());
        relevantInformation.add("Cash: " + gameInstance.getStudio().getCash().toString());
        relevantInformation.add("Number of Offices: " + gameInstance.getStudio().getOffices().size());

        TextHandler.print(relevantInformation, "Relevant information");
    }
}

