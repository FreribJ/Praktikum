package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;
import praktikum.TextHandler;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuBankruptEvaluation {
    GameDevStudio studio;

    public MenuBankruptEvaluation(GameDevStudio studio) {
        this.studio = studio;
    }

    public void showDaysUntilBankrupt(){
        Money capital = new Money(this.studio.getCash().getValue());
        Money yearlyExpenditure = new Money(new BigDecimal(0));
        for (Office office : this.studio.getOffices()) {
            yearlyExpenditure = yearlyExpenditure.add(office.getLease());
            for (Developer developer : office.getDevelopers()) {
                yearlyExpenditure = yearlyExpenditure.add(developer.getSalary());
            }
        }
        int yearsUntilDeath = 0;
        while (capital.isGreaterThan(yearlyExpenditure)) {
            capital = capital.subtract(yearlyExpenditure);
            yearsUntilDeath++;

        }
        var text = new ArrayList<String>();
        text.add(String.valueOf(yearsUntilDeath));
        TextHandler.print(text, "Remaining days until bankrupt");
    }
}
