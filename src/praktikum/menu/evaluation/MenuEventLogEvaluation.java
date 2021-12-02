package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuEventLogEvaluation {
    Game game;

    public MenuEventLogEvaluation(Game game) {
        this.game = game;
    }

    public void showEventLog() {
        var eventLog = new ArrayList<String>();
        for(String string : this.game.getEventLog()){
            eventLog.add(string);
        }
        TextHandler.print(eventLog, "Number of Events: " + eventLog.size(), "Event");
    }
}

