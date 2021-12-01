import gmbh.kdb.hsw.gdp.domain.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MenuApplication {
    GameDevStudio studio;

    public MenuApplication(GameDevStudio studio) {
        this.studio = studio;
    }

    public void showApplicationDevelopers() {
        //neue Liste für alle verfügbaren Entwickler
        var allApplicationDeveloper = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < studio.getApplications().size(); i++) {
            var developer = studio.getApplications().get(i).getDeveloper();
            allApplicationDeveloper.add(developer.toString());

            // remaining capital wird berechnet und ausgegeben
            Money capital = this.calculateRemainingCapital(studio.getApplications().get(i));

            // jährlicher Betrag den wir durch alle bisherigen Ausgaben und den neuen Entwickler verlieren wird berechnet
            Money yearlyExpenditure = this.calculateYearlyExpenditure(studio.getApplications().get(i));

            // Berechnung und Ausgabe wie viele Jahre bis tot
            int yearsUntilDeath = 0;
            while (capital.isGreaterThan(yearlyExpenditure)) {
                capital = capital.subtract(yearlyExpenditure);
                yearsUntilDeath++;
            }
            var extraInformation2D = new ArrayList<String>();
            extraInformation2D.add("Remaining capital: " + this.calculateRemainingCapital(studio.getApplications().get(i)));
            extraInformation2D.add("Years until we die: " + yearsUntilDeath);
            extraInformation.add(extraInformation2D);
        }
        TextHandler.print(allApplicationDeveloper, "These are all the applicants", null, true, extraInformation);
    }

    public Money calculateRemainingCapital(Application application) {
        Money capital = new Money(studio.getCash().getValue());
        capital = capital.subtract(application.getHireBonus());
        capital = capital.subtract(application.getHireAgentFee());
        return capital;
    }

    public Money calculateYearlyExpenditure(Application application) {
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

    public void hireApplicationDeveloper() throws WrongChoiceException{
        int developerIndex = 0;
        int officeIndex = 0;
        try {
            developerIndex = Integer.parseInt(TextHandler.getText("which one do you want to hire?")) - 1;

            var outputText = new ArrayList<String>();
            for (Office office : studio.getOffices()) {
                outputText.add(office.toString());
            }
            TextHandler.print(outputText, true);
            officeIndex = Integer.parseInt(TextHandler.getText("In which office?")) - 1;
        } catch(Exception e) {
            throw new WrongChoiceException();
        }
        if(developerIndex >= studio.getApplications().size() || developerIndex < 0 || officeIndex >= studio.getOffices().size() || officeIndex < 0){
            throw new WrongChoiceException();
        }
        studio.acceptApplication(studio.getApplications().get(developerIndex), studio.getOffices().get(officeIndex));
        TextHandler.print("Hired developer " + studio.getApplications().get(developerIndex).getDeveloper().getName().getName() + " in office " + studio.getOffices().get(officeIndex).getName().getName());
    }

    public void create() {
        var skillsAsStringArray = TextHandler.getText("Skills [separated with comma]: ").split(",");
        var skills = new Skillset(Integer.parseInt(skillsAsStringArray[0]), Integer.parseInt(skillsAsStringArray[1]), Integer.parseInt(skillsAsStringArray[2]), Integer.parseInt(skillsAsStringArray[3]));
        var hireBonus = Double.parseDouble(TextHandler.getText("Hire Bonus:"));
        var hireAgentFee = Double.parseDouble(TextHandler.getText("Hire Agent Fee:"));
        var name = TextHandler.getText("Developer Name:");
        var salary = Double.parseDouble(TextHandler.getText("Salary:"));
        var applications = new ArrayList<>(studio.getApplications());
        applications.add(SpecialApplication.createSpecialApplication(skills, hireBonus, hireAgentFee, name, salary));
        studio.setApplications(applications);
    }
}

