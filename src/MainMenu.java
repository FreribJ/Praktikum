import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Application;

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
                    this.menuApplicationStructure(null);
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
            switch (TextHandler.getText("Which Evaluation? [log; offices; developer; projects; costs; yearsUntilBankrupt]: ")) {
                case "log":
                    MenuEventLogEvaluation.showEventLog(gameInstance);
                    break;
                case "offices":
                    MenuOfficesEvaluation.showOffices(gameInstance.getStudio());
                    break;
                case "developer":
                    MenuDeveloperEvaluation.showDeveloper(gameInstance.getStudio());
                    break;
                case "projects":
                    MenuProjectsEvaluation.showProjects(gameInstance);
                    break;
                case "costs":
                    MenuCostsEvaluation.showCosts(gameInstance.getStudio());
                    break;
                case "yearsuntilbankrupt":
                    MenuBankruptEvaluation.showDaysUntilBankrupt(gameInstance.getStudio());
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
            switch (TextHandler.getText("What do you want to do? [list; accept; create; back]")) {
                case "list":
                    menuApplication = new MenuApplication(gameInstance.getStudio());
                    menuApplication.showApplicationDevelopers();
                    menuApplicationStructure(menuApplication);
                    break;
                case "accept":
                    if (menuApplication == null) {
                        throw new RuntimeException("You need to use list first");
                    }
                    menuApplication.hireApplicationDeveloper();
                    menuApplicationStructure(menuApplication);
                    break;
                case "back":
                    break;
                case "create":
                    menuApplication = new MenuApplication(gameInstance.getStudio());
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
            switch (TextHandler.getText("What do you want to do? [list; accept; create; back]")) {
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
                case "create":
                    menuProject = new MenuProject(gameInstance.getStudio());
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

