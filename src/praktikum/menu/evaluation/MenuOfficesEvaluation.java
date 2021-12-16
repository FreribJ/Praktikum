package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.Developer;
import praktikum.TextHandler;

import java.util.ArrayList;

/**
 * Handles the evaluation of the {@link Office}.
 */
public class MenuOfficesEvaluation {
    GameDevStudio studio;

    /**
     * Constructs a {@link MenuOfficesEvaluation}.
     *
     * @param studio
     */
    public MenuOfficesEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    /**
     * Prints the all the offices with name, lease and number of {@link Developer} working in.
     */
    public void showOffices() {
        var officeText = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();
        if (this.studio.getOffices().size() == 0) {
            TextHandler.print("There are no offices");
        } else {
            for (int i = 0; i < this.studio.getOffices().size(); i++) {
                var office = this.studio.getOffices().get(i);
                officeText.add("\"" + office.getName().getName() + "\"");
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
