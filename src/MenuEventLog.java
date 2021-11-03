import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;

import java.util.List;

public class MenuEventLog {

    public static void showEventLog(Game game){
        List<String> list = game.getEventLog();
        System.out.println("Number of Events: " + list.size());
        for(String eventLog : list)
           System.out.println("Event : " + eventLog);
    }
}

