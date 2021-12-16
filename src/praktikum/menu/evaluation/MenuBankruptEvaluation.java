package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import praktikum.TextHandler;

import java.util.ArrayList;

/**
 * Handles the evaluation of the days until bankrupt.
 */
public class MenuBankruptEvaluation {
    GameDevStudio studio;

    /**
     * Constructs a {@link MenuBankruptEvaluation}.
     *
     * @param studio the studio operated with
     */
    public MenuBankruptEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    /**
     * Calculates and prints the days until bankrupt.
     */
    public void showDaysUntilBankrupt() {
        var text = new ArrayList<String>();
        text.add(String.valueOf(this.getDaysUntilBankrupt()));
        TextHandler.print(text, "Remaining days until bankrupt");
    }

    /**
     * Calculates the days until bankrupt.
     *
     * @return the days until bankrupt
     */
    public int getDaysUntilBankrupt() {
        var capital = new Money(this.studio.getCash().getValue());
        var dailyExpenditure = new MenuCostsEvaluation(studio).getCosts();
        int daysUntilDeath = 0;
        while (capital.isGreaterThan(dailyExpenditure)) {
            capital = capital.subtract(dailyExpenditure);
            daysUntilDeath++;

        }
        return daysUntilDeath;
    }
}
