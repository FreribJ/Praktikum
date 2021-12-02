package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import praktikum.TextHandler;
import praktikum.exceptions.WrongChoiceException;
import praktikum.menu.evaluation.*;

import java.util.ArrayList;

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

    //praktikum.Main-Menu
    public void menuStructure() {
        try {
            switch (TextHandler.getText("Whats your next choice? [continue(c); evaluation(e); applications(a); projects(p); continueAll(ca); end]: ").toLowerCase()) {
                case "end":
                    //Terminate program
                    TextHandler.print("end");
                    break;
                case "ca":
                case "continueall":
                    TextHandler.print("continues");
                    letGameContinue = true;
                    gameInstance.start();
                    break;
                case "e":
                case "evaluation":
                    this.menuEvaluationStructure();
                    menuStructure();
                    break;
                case "a":
                case "applications":
                    this.menuApplicationStructure(null);
                    menuStructure();
                    break;
                case "p":
                case "projects":
                    this.menuProjectsStructure(null);
                    menuStructure();
                    break;
                case "c":
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
            switch (TextHandler.getText("Which Evaluation? [log(l); offices(o); developer(d); projects(p); costs(c); yearsUntilBankrupt(y); back(b)]: ").toLowerCase()) {
                case "l":
                case "log":
                    MenuEventLogEvaluation.showEventLog(gameInstance);
                    menuEvaluationStructure();
                    break;
                case "o":
                case "offices":
                    MenuOfficesEvaluation.showOffices(gameInstance.getStudio());
                    menuEvaluationStructure();
                    break;
                case "d":
                case "developer":
                    MenuDeveloperEvaluation.showDeveloper(gameInstance.getStudio());
                    menuEvaluationStructure();
                    break;
                case "p":
                case "projects":
                    MenuProjectsEvaluation.showProjects(gameInstance);
                    menuEvaluationStructure();
                    break;
                case "c":
                case "costs":
                    MenuCostsEvaluation.showCosts(gameInstance.getStudio());
                    menuEvaluationStructure();
                    break;
                case "y":
                case "yearsuntilbankrupt":
                    MenuBankruptEvaluation.showDaysUntilBankrupt(gameInstance.getStudio());
                    menuEvaluationStructure();
                    break;
                case "b":
                case "back":
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            menuEvaluationStructure();
        }
    }

    public void menuApplicationStructure(MenuApplication menuApplication) {
        try {
            switch (TextHandler.getText("What do you want to do? [list(l); accept(a); create(c); back(b)]").toLowerCase()) {
                case "l":
                case "list":
                    menuApplication = new MenuApplication(gameInstance);
                    menuApplication.showApplicationDevelopers();
                    menuApplicationStructure(menuApplication);
                    break;
                case "a":
                case "accept":
                    if (menuApplication == null) {
                        throw new RuntimeException("You need to use list first");
                    }
                    menuApplication.hireApplicationDeveloper();
                    menuApplicationStructure(null);
                    break;
                case "b":
                case "back":
                    break;
                case "c":
                case "create":
                    menuApplication = new MenuApplication(gameInstance);
                    menuApplication.create();
                    gameInstance.actionPerformed();
                    menuApplicationStructure(null);
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            menuApplicationStructure(menuApplication);
        }
        catch (NumberFormatException e) {
            System.out.println("You have to give a number!");
            menuApplicationStructure(menuApplication);
        }
        catch (RuntimeException e) {
            System.out.println(e.getMessage());
            menuApplicationStructure(menuApplication);
        }


    }

    public void menuProjectsStructure(MenuProject menuProject) {
        try {
            switch (TextHandler.getText("What do you want to do? [list(l); accept(a); create(c); back(b)]").toLowerCase()) {
                case "l":
                case "list":
                    menuProject = new MenuProject(gameInstance);
                    menuProject.showProjects();
                    menuProjectsStructure(menuProject);
                    break;
                case "a":
                case "accept":
                    if (menuProject == null) {
                        throw new RuntimeException("You need to use List first");
                    }
                    menuProject.accept(Integer.parseInt(TextHandler.getText("Which number?")));
                    menuProjectsStructure(null);
                    break;
                case "b":
                case "back":
                    break;
                case "c":
                case "create":
                    menuProject = new MenuProject(gameInstance);
                    menuProject.create();
                    gameInstance.actionPerformed();
                    menuProjectsStructure(null);
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

