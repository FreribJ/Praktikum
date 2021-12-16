package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import praktikum.TextHandler;
import praktikum.exceptions.NotAvailableException;
import praktikum.exceptions.WrongChoiceException;
import praktikum.menu.evaluation.*;

import java.util.ArrayList;

/**
 * Handles the main menu and all submenus.
 */
public class MainMenu {

    Game gameInstance;
    boolean letGameContinue = false;
    MenuEventLogEvaluation menuEventLogEvaluation;
    MenuOfficesEvaluation menuOfficesEvaluation;
    MenuDeveloperEvaluation menuDeveloperEvaluation;
    MenuProjectsEvaluation menuProjectsEvaluation;
    MenuCostsEvaluation menuCostsEvaluation;
    MenuBankruptEvaluation menuBankruptEvaluation;

    /**
     * Starts the game and the {@link #menuStructure()}.
     */
    public void run() {
        gameInstance = Game.create(iGameHandler -> {
            this.printRelevantInformation();
            return letGameContinue;
        });
        gameInstance.start();
        menuEventLogEvaluation = new MenuEventLogEvaluation(gameInstance);
        menuOfficesEvaluation = new MenuOfficesEvaluation(gameInstance.getStudio());
        menuDeveloperEvaluation = new MenuDeveloperEvaluation(gameInstance.getStudio());
        menuProjectsEvaluation = new MenuProjectsEvaluation(gameInstance);
        menuCostsEvaluation = new MenuCostsEvaluation(gameInstance.getStudio());
        menuBankruptEvaluation = new MenuBankruptEvaluation(gameInstance.getStudio());
        menuStructure();
    }

    /**
     * Prints the main menu and calls the submenus.
     */
    private void menuStructure() {
        try {
            switch (TextHandler.getText("(Main Menu) " + "Whats your next choice? [continue(c); evaluation(e); applications(a); projects(p); offices(o); continue all(ca); end]: ").toLowerCase()) {
                case "end":
                    TextHandler.print("The game has ended. These are your results: ");
                    printRelevantInformation();
                    break;
                case "ca":
                case "continue all":
                    TextHandler.print("continues");
                    letGameContinue = true;
                    gameInstance.start();
                    break;
                case "e":
                case "evaluation":
                    this.menuEvaluationStructure();
                    this.menuStructure();
                    break;
                case "a":
                case "applications":
                    this.menuApplicationStructure(null);
                    this.menuStructure();
                    break;
                case "p":
                case "projects":
                    this.menuProjectsStructure(null);
                    this.menuStructure();
                    break;
                case "c":
                case "continue":
                    gameInstance.start();
                    this.menuStructure();
                    break;
                case "o":
                case "offices":
                    this.menuOfficesStructure();
                    this.menuStructure();
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            this.menuStructure();
        }
    }

    /**
     * Prints the evaluation menu and handles all evaluations.
     */
    private void menuEvaluationStructure() {
        try {
            switch (TextHandler.getText("(Evaluation Menu) " + "What do you want to evaluate? [log(l); offices(o); developer(d); projects(p); costs(c); daysUntilBankrupt(db); back(b)]: ").toLowerCase()) {
                case "l":
                case "log":
                    menuEventLogEvaluation.showEventLog();
                    this.menuEvaluationStructure();
                    break;
                case "o":
                case "offices":
                    menuOfficesEvaluation.showOffices();
                    this.menuEvaluationStructure();
                    break;
                case "d":
                case "developer":
                    menuDeveloperEvaluation.showDeveloper();
                    this.menuEvaluationStructure();
                    break;
                case "p":
                case "projects":
                    menuProjectsEvaluation.showProjects();
                    this.menuEvaluationStructure();
                    break;
                case "c":
                case "costs":
                    menuCostsEvaluation.showCosts();
                    this.menuEvaluationStructure();
                    break;
                case "db":
                case "daysuntilbankrupt":
                    menuBankruptEvaluation.showDaysUntilBankrupt();
                    this.menuEvaluationStructure();
                    break;
                case "b":
                case "back":
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException e) {
            System.out.println(e.getMessage());
            this.menuEvaluationStructure();
        }
    }

    /**
     * Prints the application menu and handles the {@link MenuApplication}.
     */
    private void menuApplicationStructure(MenuApplication menuApplication) {
        try {
            switch (TextHandler.getText("(Application Menu) " + "What do you want to do? [list(l); accept(a); create(c); back(b)]").toLowerCase()) {
                case "l":
                case "list":
                    menuApplication = new MenuApplication(gameInstance);
                    menuApplication.showApplicationDevelopers();
                    this.menuApplicationStructure(menuApplication);
                    break;
                case "a":
                case "accept":
                    if (menuApplication == null) {
                        throw new NotAvailableException("You need to use list first");
                    }
                    gameInstance.assertActionsLeft();
                    menuApplication.hireApplicationDeveloper();
                    this.menuApplicationStructure(null);
                    break;
                case "b":
                case "back":
                    break;
                case "c":
                case "create":
                    gameInstance.assertActionsLeft();
                    menuApplication = new MenuApplication(gameInstance);
                    menuApplication.create();
                    gameInstance.actionPerformed();
                    this.menuApplicationStructure(null);
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException | NotAvailableException | IllegalStateException e) {
            System.out.println(e.getMessage());
            this.menuApplicationStructure(menuApplication);
        }


    }

    /**
     * Prints the projects menu and handles the {@link MenuProject}.
     */
    private void menuProjectsStructure(MenuProject menuProject) {
        try {
            switch (TextHandler.getText("(Project Menu) " + "What do you want to do? [list(l); accept(a); create(c); back(b)]").toLowerCase()) {
                case "l":
                case "list":
                    menuProject = new MenuProject(gameInstance);
                    menuProject.showProjects();
                    this.menuProjectsStructure(menuProject);
                    break;
                case "a":
                case "accept":
                    if (menuProject == null) {
                        throw new NotAvailableException("You need to use List first");
                    }
                    gameInstance.assertActionsLeft();
                    menuProject.accept();
                    this.menuProjectsStructure(null);
                    break;
                case "b":
                case "back":
                    break;
                case "c":
                case "create":
                    gameInstance.assertActionsLeft();
                    menuProject = new MenuProject(gameInstance);
                    menuProject.create();
                    gameInstance.actionPerformed();
                    this.menuProjectsStructure(null);
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException | NotAvailableException | IllegalStateException e) {
            System.out.println(e.getMessage());
            this.menuProjectsStructure(menuProject);
        }

    }

    /**
     * Prints the office menu and handles the {@link MenuOffice}.
     */
    private void menuOfficesStructure() {
        try {
            switch (TextHandler.getText("(Office Menu) " + "What do you want to do? [create(c); back(b)]").toLowerCase()) {
                case "b":
                case "back":
                    break;
                case "c":
                case "create":
                    gameInstance.assertActionsLeft();
                    var menuOffice = new MenuOffice(gameInstance);
                    menuOffice.create();
                    gameInstance.actionPerformed();
                    this.menuOfficesStructure();
                    break;
                default:
                    throw new WrongChoiceException();
            }
        } catch (WrongChoiceException | IllegalStateException e) {
            System.out.println(e.getMessage());
            this.menuOfficesStructure();
        }
    }


    /**
     * Prints all relevant information for each day.
     */
    private void printRelevantInformation() {
        var relevantInformation = new ArrayList<String>();
        relevantInformation.add("Company Name: " + gameInstance.getStudio().getName().getName());
        relevantInformation.add("Cash: " + gameInstance.getStudio().getCash().toString());
        relevantInformation.add("Number of Offices: " + gameInstance.getStudio().getOffices().size());
        relevantInformation.add("Number of Day: " + gameInstance.getDay().getNumber());

        TextHandler.print(relevantInformation, "Relevant information");
    }
}

