import gmbh.kdb.hsw.gdp.domain.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;

public class MenuApplication {
    public static void showApplicationDevelopers(GameDevStudio studio) {

        //neue Liste für alle verfügbaren Entwickler
        var allApplicationDeveloper = new ArrayList<Developer>();

        //alle Applications werden durchgegangen und der jeweilige Entwickler zur Liste aller verfügbaren Entwickler hinzugefügt
        var applications = new ArrayList<Application>();
        for(int a = 0; a < studio.getApplications().size(); a++){
            applications.add(studio.getApplications().get(a));
        }

        for (int i = 0; i < applications.size(); i++) {
            var developer = applications.get(i).getDeveloper();
            allApplicationDeveloper.add(developer);
        }

        //Liste aller verfügbaren Entwickler wird durchgegangen
        for (int j = 0; j < allApplicationDeveloper.size(); j++) {
            // jeder Entwickler wird ausgegeben
            System.out.println(j + ": " + allApplicationDeveloper.get(j).toString());

            // remaining capital wird berechnet und ausgegeben
            Money capital = calculateRemainingCapital(studio, j, applications);

            // jährlicher Betrag den wir durch alle bisherigen Ausgaben und den neuen Entwickler verlieren wird berechnet
            Money yearlyExpenditure = calculateyearlyExpenditure(studio, j, applications);

            // Berechnung und Ausgabe wie viele Jahre bis tot
            int yearsUntilDeath = 0;
            while (capital.isGreaterThan(yearlyExpenditure)) {
                capital = capital.subtract(yearlyExpenditure);
                yearsUntilDeath++;

            }
            System.out.println("Years until we die: " + yearsUntilDeath);
        }
    }

    public static Money calculateRemainingCapital(GameDevStudio studio, int j, ArrayList<Application> applications) {
        Money capital = new Money(new BigDecimal(0));
        capital = capital.add(studio.getCash());
        capital = capital.subtract(applications.get(j).getHireBonus());
        System.out.println("Remaining capital: " + capital.toString());
        return capital;
    }

    public static Money calculateyearlyExpenditure(GameDevStudio studio, int j, ArrayList<Application> applications) {
        // jährlicher Betrag den wir durch alle Ausgaben verlieren
        Money yearlyExpenditure = new Money(new BigDecimal(0));
        for (Office office : studio.getOffices()) {
            yearlyExpenditure = yearlyExpenditure.add(office.getLease());
            for (Developer developer : office.getDevelopers()) {
                yearlyExpenditure = yearlyExpenditure.add(developer.getSalary());
            }
        }

        //jährlicher Betrag den wir zusätzlich durch das Gehalt des jeweiligen Entwicklers ausgeben
        yearlyExpenditure = yearlyExpenditure.add(applications.get(j).getHireAgentFee());
        return yearlyExpenditure;
    }

    public static void hireApplicationDeveloper(GameDevStudio studio){
        int developerIndex = Integer.parseInt(TextHandler.getText("which one do you want to hire?"));
        for (int j = 0; j< studio.getOffices().size(); j++) {
            System.out.println(j + ": " + studio.getOffices().get(j).toString());
        }
        int officeIndex = Integer.parseInt(TextHandler.getText("In which office?"));
        studio.acceptApplication(studio.getApplications().get(developerIndex), studio.getOffices().get(officeIndex));
    }
}

