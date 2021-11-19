import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;

import java.util.ArrayList;

public class MenuApplication {
    public static void showApplicationDevelopers(GameDevStudio studio){

        var allApplicationDeveloper = new ArrayList<Developer>();

        var applications = studio.getApplications();
        for (int i = 0; i < applications.size(); i++) {
            var developer = applications.get(i).getDeveloper();
            allApplicationDeveloper.add(developer);
        }

        //System.out.println("Number of Developer: " + allApplicationDeveloper.size());
        for (int j = 0; j < allApplicationDeveloper.size(); j++) {
            System.out.println(j+ ": " + allApplicationDeveloper.get(j).toString());
        }
    }
    }

