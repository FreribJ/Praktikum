import gmbh.kdb.hsw.gdp.Game;

import java.sql.SQLOutput;
import java.util.Locale;
import java.util.Scanner;

public class MainMenu {

    Game gameInstance;
    public void run() {
        gameInstance = Game.create(IGameHandler -> (false));
        gameInstance.start();
        System.out.println(gameInstance);
        menuStructure();
    }
    public void menuStructure() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Was ist Ihre nächste Wahl?");
        try {
            String input = sc.next().toLowerCase();
            switch (input) {
                case "beenden":
                    //Mach das es Endet!
                    System.out.println("Ende");
                    break;
                case "weiter":
                    System.out.println("Geht weiter");
                    while (true){
                        gameInstance.start();
                        System.out.println(gameInstance);
                    }
                default:
                    throw new WrongChoiceException("Falsche Eingabe");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

