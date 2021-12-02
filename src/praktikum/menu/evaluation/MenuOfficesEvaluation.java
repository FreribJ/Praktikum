package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;
import praktikum.TextHandler;

import java.util.ArrayList;

public class MenuOfficesEvaluation {
    GameDevStudio studio;

    public MenuOfficesEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    public void showOffices(){
        var officeText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();
        if (this.studio.getOffices().size() == 0) { //Überprüft ob
            TextHandler.print("There are no offices");
        } else {
            for (int i = 0; i < this.studio.getOffices().size(); i++) { //Iterate throw all Offices
                Office office = this.studio.getOffices().get(i);
                officeText.add("\""+office.getName().getName()+"\"");
                var extraInformation2D = new ArrayList<String>();
                if (office.getLease() == null) {
                    extraInformation2D.add("There is no Lease");
                } else {
                    extraInformation2D.add("Lease: " + office.getLease());
                    extraInformation2D.add("Number of developers: " + office.getDevelopers().size());
                    extraInformation2D.add("");
                }
                extraInformation.add(extraInformation2D);
            }
            TextHandler.print(officeText, "Offices", "Office", true, extraInformation);
        }
    }
}
