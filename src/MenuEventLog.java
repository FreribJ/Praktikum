import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;

import java.util.List;

public class MenuEventLog {

    public static void showEventLog(GameDevStudio studio){
        List<Project> list = studio.getProjectBoard().get();
        System.out.println("Number of projects: " + list.size());
        for(Project project : list)
           System.out.println("Project : " + project.toString());
    }
}

