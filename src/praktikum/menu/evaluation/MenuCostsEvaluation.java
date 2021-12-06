package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;
import praktikum.TextHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Handles the evaluation of the daily costs.
 */
public class MenuCostsEvaluation {
    GameDevStudio studio;

    /**
     * Constructs a {@link MenuCostsEvaluation}.
     * @param studio the studio operated with.
     */
    public MenuCostsEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    /**
     * Calculates and prints the daily costs.
     */
    public void showCosts(){
        var text = new ArrayList<String>();
        text.add(this.getCosts().toString());
        TextHandler.print(text, "Sum of running costs");
    }

    /**
     * Calculates the daily costs.
     * @return the daily costs.
     */
    public Money getCosts(){
        Money dailyExpenditure = new Money(new BigDecimal(0));
        for (Office office : this.studio.getOffices()) {
            dailyExpenditure = dailyExpenditure.add(office.getLease());
            for (Developer developer : office.getDevelopers()) {
                dailyExpenditure = dailyExpenditure.add(developer.getSalary());
            }
        }
        return dailyExpenditure;
    }
}
