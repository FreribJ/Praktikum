import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

import java.util.ArrayList;

public class MenuOfficesEvaluation {
    public static void showOffices(GameDevStudio studio){
        var outputText = new ArrayList<String>();
        for (Office office : studio.getOffices()) {
            outputText.add("");
            outputText.add("Name: " + office.getName().getName());
            outputText.add("Lease: " + office.getLease().toString());
            outputText.add("Number of developer: " + office.getDevelopers().size());
        }
        TextHandler.print(outputText, "Number of offices: " + studio.getOffices().size());
    }
}
