package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;
import praktikum.TextHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuCostsEvaluation {
    GameDevStudio studio;

    public MenuCostsEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    public void showCosts(){
        Money yearlyExpenditure = new Money(new BigDecimal(0));
        for (Office office : this.studio.getOffices()) {
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
