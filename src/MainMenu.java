import gmbh.kdb.hsw.gdp.Game;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    Game gameInstance;
    public void run() {
        gameInstance = Game.create(iGameHandler -> {
            printRelevantInformation();
            return false;
        });

        gameInstance.start();
        menuStructure();
    }
    public void menuStructure() {
        try {
            switch (TextHandler.getText("Whats your next choice?: ")) {
                case "end":
                    //Terminate program
                    System.out.println("end");
                    break;
                case "continue":
                    System.out.println("continues");
                    while (true){
                        gameInstance.start();
                    }
                default:
                    throw new WrongChoiceException("Wrong input");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void printRelevantInformation() {
        System.out.println("Companyname: " + gameInstance.getStudio().getName().getName());
        System.out.println("Cash: " + gameInstance.getStudio().getCash().toString());
        System.out.println("Number of Offices: " + gameInstance.getStudio().getOffices().size());
    }
}

