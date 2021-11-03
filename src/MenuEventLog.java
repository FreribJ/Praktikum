import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;

import java.util.List;

public class MenuEventLog {

    public static void showEventLog(GameDevStudio eventLog){
        List<Project> liste = eventLog.getProjectBoard().get();
        for(int i=0; i<liste.size(); i++)
           System.out.println(liste.get(i).getName());
    }
}

