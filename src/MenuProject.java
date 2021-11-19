
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Project;
import gmbh.kdb.hsw.gdp.domain.Skillset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuProject {
    public static void showProjects(GameDevStudio studio) {
        var allProjects = studio.getProjectBoard().get();

        var offices = studio.getOffices();
        var allDeveloperWithoutProject = new ArrayList<Developer>();
        for (int i = 0; i < offices.size(); i++) {
            var developer = offices.get(i).getDevelopers();
            for (int k = 0; k < developer.size(); k++) {
                var dev = developer.get(k);
                if (dev.getWorkingOn() == null) {
                    allDeveloperWithoutProject.add(dev);
                }
            }
        }

        System.out.println("Anzahl verfügbare Entwickler: " + allDeveloperWithoutProject.size());


        for (int i = 0; i < allProjects.size(); i++) {
            System.out.println(allProjects.get(i));
            for (int j = 0; j < allDeveloperWithoutProject.size(); j++) {
                System.out.println("Days to Finish: " + getDaysForFinish(allDeveloperWithoutProject.get(j), allProjects.get(i)));
                System.out.println(allProjects.get(i));
            }
        }
    }

    public static int getDaysForFinish(Developer dev, Project pro) {
        Skillset effort = pro.getEffort();
        int days = 0;
        while (effort.getCoding() != 0 && effort.getDesign() != 0 && effort.getResearch() != 0 && effort.getTesting() != 0) {
            days++;
            effort.subtract(dev.getSkills());
        }
        return days;
    }

}
