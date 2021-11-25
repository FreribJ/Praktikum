import gmbh.kdb.hsw.gdp.domain.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuApplication {
    public static void showApplicationDevelopers(GameDevStudio studio) {

        //neue Liste für alle verfügbaren Entwickler
        var allApplicationDeveloper = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < studio.getApplications().size(); i++) {
            var developer = studio.getApplications().get(i).getDeveloper();
            allApplicationDeveloper.add(developer.toString());

            // remaining capital wird berechnet und ausgegeben
            Money capital = calculateRemainingCapital(studio, studio.getApplications().get(i));

            // jährlicher Betrag den wir durch alle bisherigen Ausgaben und den neuen Entwickler verlieren wird berechnet
            Money yearlyExpenditure = calculateYearlyExpenditure(studio, studio.getApplications().get(i));

            // Berechnung und Ausgabe wie viele Jahre bis tot
            int yearsUntilDeath = 0;
            while (capital.isGreaterThan(yearlyExpenditure)) {
                capital = capital.subtract(yearlyExpenditure);
                yearsUntilDeath++;

            }
            var extraInformation2D = new ArrayList<String>();
            extraInformation2D.add("Remaining capital: " + calculateRemainingCapital(studio, studio.getApplications().get(i)));
            extraInformation2D.add("Years until we die: " + yearsUntilDeath);
            extraInformation.add(extraInformation2D);
        }
        TextHandler.print(allApplicationDeveloper, "These are all the applicants", "", true, extraInformation);
    }

    public static Money calculateRemainingCapital(GameDevStudio studio, Application application) {
        Money capital = new Money(studio.getCash().getValue());
        capital = capital.subtract(application.getHireBonus());
        capital = capital.subtract(application.getHireAgentFee());
        return capital;
    }

    public static Money calculateYearlyExpenditure(GameDevStudio studio, Application application) {
        // jährlicher Betrag den wir durch alle Ausgaben verlieren
        Money yearlyExpenditure = new Money(new BigDecimal(0));
        for (Office office : studio.getOffices()) {
            yearlyExpenditure = yearlyExpenditure.add(office.getLease());
            for (Developer developer : office.getDevelopers()) {
                yearlyExpenditure = yearlyExpenditure.add(developer.getSalary());
            }
        }

        //jährlicher Betrag den wir zusätzlich durch das Gehalt des jeweiligen Entwicklers ausgeben
        yearlyExpenditure = yearlyExpenditure.add(application.getDeveloper().getSalary());
        return yearlyExpenditure;
    }

    public static void hireApplicationDeveloper(GameDevStudio studio){
        int developerIndex = Integer.parseInt(TextHandler.getText("which one do you want to hire?")) - 1;
        var outputText = new ArrayList<String>();
        for (Office office : studio.getOffices()) {
            outputText.add(office.toString());
        }
        TextHandler.print(outputText, true);
        int officeIndex = Integer.parseInt(TextHandler.getText("In which office?")) - 1;
        studio.acceptApplication(studio.getApplications().get(developerIndex), studio.getOffices().get(officeIndex));
        TextHandler.print("Hired developer " + studio.getApplications().get(developerIndex).getDeveloper().getName().getName() + " in office " + studio.getOffices().get(officeIndex).getName().getName());
    }
}

