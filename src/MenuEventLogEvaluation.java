import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;

import java.util.ArrayList;
import java.util.List;

public class MenuEventLogEvaluation {
    public static void showEventLog(Game game) {
        var eventLog = new ArrayList<String>();
        for(String string: game.getEventLog()){
            eventLog.add(string);
        }
        TextHandler.print(eventLog, "Number of Events: " + eventLog.size(), "Event");
    }
}

