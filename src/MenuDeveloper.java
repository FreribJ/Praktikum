import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;

import java.util.ArrayList;
import java.util.List;

public class MenuDeveloper {
    public static void showDeveloper(GameDevStudio studio) {
        var allDeveloper = new ArrayList<Developer>();

        var offices = studio.getOffices();
        for (int i = 0; i < offices.size(); i++) {
            var developer = offices.get(i).getDevelopers();
            for (int k = 0; k < developer.size(); k++) {
                allDeveloper.add(developer.get(k));
            }
        }
        System.out.println("Number of Developer: " + allDeveloper.size());
        for (int i = 0; i < allDeveloper.size(); i++) {
            System.out.println((i + 1) + ". " + allDeveloper.get(i));
        }
    }
}
