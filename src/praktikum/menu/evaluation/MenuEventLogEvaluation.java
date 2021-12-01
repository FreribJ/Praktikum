package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuEventLogEvaluation {
    public static void showEventLog(Game game) {
        var eventLog = new ArrayList<String>();
        for(String string: game.getEventLog()){
            eventLog.add(string);
        }
        TextHandler.print(eventLog, "Number of Events: " + eventLog.size(), "Event");
    }
}

