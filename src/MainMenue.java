import gmbh.kdb.hsw.gdp.Game;

import java.sql.SQLOutput;
import java.util.Scanner;

public class MainMenue {
    Game gameInstanz;
    public void run() {
        gameInstanz = Game.create(IGameHandler -> (false));
        gameInstanz.start();
        System.out.println(gameInstanz);
        menuStructure();
    }

    public void menuStructure() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Was ist ihre nächste Wahl?");
        try {
            String eingabe = sc.next();

            switch (eingabe) {
                case "Beenden":
                    //Mach das es Endet!
                    System.out.println("Ende");
                    break;
                case "Weiter":
                    System.out.println("Geht weiter");
                    while (true){
                        gameInstanz.start();
                        System.out.println(gameInstanz);
                    }
                default:
                    throw new WrongChoiceException("Falsche Eingabe");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

