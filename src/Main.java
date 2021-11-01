import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.IGameHandler;

public class Main {
    public static void main(String[] args) {
        Game.create(IGameHandler -> (true));
    }
}
