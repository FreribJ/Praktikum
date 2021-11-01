import gmbh.kdb.hsw.gdp.Game;

public class MainMenue {
    public void run(){
        var gameInstanz = Game.create(IGameHandler -> (true));
        gameInstanz.start();
    }
}
