
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;

import java.util.ArrayList;

public class MenuProject {
    public static void showProjects(GameDevStudio studio) {
        var allProjects = studio.getProjectBoard().get();

        for (int i = 0; i < allProjects.size(); i++) {
            System.out.println(allProjects.get(i));
        }
    }

}
