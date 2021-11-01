import gmbh.kdb.hsw.gdp.Game;

import java.util.Scanner;

public class MainMenue {
    public void run() {
        var gameInstanz = Game.create(IGameHandler -> (true));
        menuStructure();
        gameInstanz.start();
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
                    //Macht, dass es weiter geht!
                    System.out.println("Geht weiter");
                    break;
                default:
                    throw new WrongChoiceException("Falsche Eingabe");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

