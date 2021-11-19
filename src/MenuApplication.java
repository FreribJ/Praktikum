import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuApplication {
    public static void showApplicationDevelopers(GameDevStudio studio){

        var allApplicationDeveloper = new ArrayList<Developer>();

        var applications = studio.getApplications();
        for (int i = 0; i < applications.size(); i++) {
            var developer = applications.get(i).getDeveloper();
            allApplicationDeveloper.add(developer);
        }

        //System.out.println("Number of Developer: " + allApplicationDeveloper.size());
        for (int j = 0; j < allApplicationDeveloper.size(); j++) {
            System.out.println(j+ ": " + allApplicationDeveloper.get(j).toString());
            Money capital = new Money(new BigDecimal(0));
            capital = capital.add(studio.getCash());

            capital = capital.subtract(applications.get(j).getHireBonus());
            System.out.println("Remaining capital: " + capital.toString());
            Money yearlyExpenditure = new Money(new BigDecimal(0));
            for (Office office : studio.getOffices()) {
                yearlyExpenditure = yearlyExpenditure.add(office.getLease());
                for (Developer developer : office.getDevelopers()) {
                    yearlyExpenditure = yearlyExpenditure.add(developer.getSalary());
                }
            }
            yearlyExpenditure = yearlyExpenditure.add(applications.get(j).getHireAgentFee());
            int yearsUntilDeath = 0;
            while (capital.isGreaterThan(yearlyExpenditure)){
                capital = capital.subtract(yearlyExpenditure);
                yearsUntilDeath++;

            }
            System.out.println("Years until we die: " + yearsUntilDeath);
        }
    }
    }

