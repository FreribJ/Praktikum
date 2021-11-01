import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.IGameHandler;

public class Main {
    public static void main(String[] args) {
        //var gameInstanz = Game.create(IGameHandler -> (true));
        //gameInstanz.start();

        var menu = new MainMenue();
        menu.run();
    }
}
