import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuCostsEvaluation {
    public static void showCosts(GameDevStudio studio){
        Money yearlyExpenditure = new Money(new BigDecimal(0));
        for (Office office : studio.getOffices()) {
            yearlyExpenditure = yearlyExpenditure.add(office.getLease());
            for (Developer developer : office.getDevelopers()) {
                yearlyExpenditure = yearlyExpenditure.add(developer.getSalary());
            }
        }
        var text = new ArrayList<String>();
        text.add(yearlyExpenditure.toString());
        TextHandler.print(text, "Sum of running costs");
    }
}
