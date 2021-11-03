import gmbh.kdb.hsw.gdp.Game;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    Game gameInstance;
    public void run() {
        gameInstance = Game.create(gameDevStudio -> {
            System.out.println(gameInstance);
            return false;
        });
        gameInstance.start();
        menuStructure();
    }
    public void menuStructure() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Whats your next choice?");
        try {
            String input = sc.next().toLowerCase();
            switch (input) {
                case "end":
                    //Terminate programm
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
}

