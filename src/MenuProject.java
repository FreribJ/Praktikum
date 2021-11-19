
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

        for (int i = 0; i < allProjects.size(); i++) {
            System.out.println(allProjects.get(i));
            for (int j = 0; j < allDeveloperWithoutProject.size(); j++) {
                var dev = allDeveloperWithoutProject.get(j);
                try {
                    System.out.print("Days to Finish: " + getDaysForFinish(dev, allProjects.get(i)) + " -> ");
                }
                catch (RuntimeException e) {
                    System.out.print(e.getMessage() + " -> ");
                }
                System.out.println(dev);
            }
            System.out.println();
        }
    }

    public static int getDaysForFinish(Developer dev, Project pro) {
        Skillset effort = pro.getEffort();
        int days = 0;
        if(
                (dev.getSkills().getCoding() == 0 && effort.getCoding() != 0) ||
                (dev.getSkills().getResearch() == 0 && effort.getResearch() != 0) ||
                (dev.getSkills().getTesting() == 0 && effort.getTesting() != 0) ||
                (dev.getSkills().getDesign() == 0 && effort.getDesign() != 0)
        ) {
            throw new RuntimeException("Developer is unable to perform this project!");
        }
        while (effort.getCoding() != 0 || effort.getDesign() != 0 || effort.getResearch() != 0 || effort.getTesting() != 0) {
            days++;
            effort = effort.subtract(dev.getSkills());
        }
        return days;
    }

}
