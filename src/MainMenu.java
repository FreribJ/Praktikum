import gmbh.kdb.hsw.gdp.Game;
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

    public void menuStructure() {
        try {
            switch (TextHandler.getText("Whats your next choice? [end, continue; evaluation]: ")) {
                case "end":
                    //Terminate program
                    System.out.println("end");
                    break;
                case "continue":
                    System.out.println("continues");
                    letGameContinue = true;
                    gameInstance.start();
                    break;
                case "evaluation":
                    this.menuEvaluationStructure();
                    break;
                default:
                    throw new WrongChoiceException("Wrong input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuEvaluationStructure() {
        try {
            switch (TextHandler.getText("Which Evaluation? [log; offices; developer]: ")) {
                case "log":
                    MenuEventLog.showEventLog(gameInstance.getStudio());
                    break;
                case "offices":
                    MenuOffices.showOffices(gameInstance.getStudio());
                    break;
                case "developer":
                    MenuDeveloper.showDeveloper(gameInstance.getStudio());
                    break;
                default:
                    throw new WrongChoiceException("Wrong input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void printRelevantInformation() {
        System.out.println("Relevant information: ");
        System.out.println("Companyname: " + gameInstance.getStudio().getName().getName());
        System.out.println("Cash: " + gameInstance.getStudio().getCash().toString());
        System.out.println("Number of Offices: " + gameInstance.getStudio().getOffices().size());
        System.out.println();
    }
}

