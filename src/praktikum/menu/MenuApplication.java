package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.*;
import praktikum.exceptions.NotAvailableException;
import praktikum.special.SpecialApplication;
import praktikum.TextHandler;
import praktikum.exceptions.WrongChoiceException;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Handles all {@link Application} related operations.
 */
public class MenuApplication {
    Game game;
    GameDevStudio studio;

    /**
     * Constructor of a {@link MenuApplication}.
     *
     * @param game the game operated with
     */
    public MenuApplication(Game game) {
        this.game = game;
        this.studio = game.getStudio();
    }

    /**
     * Prints all {@link Application} with important information about the application.
     */
    public void showApplicationDevelopers() {
        var allApplicationDeveloper = new ArrayList<String>();
        var extraInformation = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < studio.getApplications().size(); i++) {
            var developer = studio.getApplications().get(i).getDeveloper();
            allApplicationDeveloper.add(developer.getName().getName());

            var capital = this.calculateRemainingCapital(studio.getApplications().get(i));

            var yearlyExpenditure = this.calculateDailyExpenditure(studio.getApplications().get(i));

            int daysUntilDeath = 0;
            while (capital.isGreaterThan(yearlyExpenditure)) {
                capital = capital.subtract(yearlyExpenditure);
                daysUntilDeath++;
            }
            var extraInformation2D = new ArrayList<String>();
            extraInformation2D.add("Hire Bonus: " + studio.getApplications().get(i).getHireBonus());
            extraInformation2D.add("Hire Agent Fee: " + studio.getApplications().get(i).getHireAgentFee());
            extraInformation2D.add("Salary: " + studio.getApplications().get(i).getDeveloper().getSalary());
            extraInformation2D.add("Remaining capital: " + this.calculateRemainingCapital(studio.getApplications().get(i)));
            extraInformation2D.add("Days until we die: " + daysUntilDeath);
            extraInformation.add(extraInformation2D);
        }
        if (allApplicationDeveloper.isEmpty()) {
            TextHandler.print("There are no applications!");
        } else {
            TextHandler.print(allApplicationDeveloper, "These are all the applicants", null, true, extraInformation);

        }
    }

    /**
     * Calculates the remaining capital if you accept the {@link Application}.
     *
     * @param application is the application calculating with
     * @return the remaining capital
     */
    private Money calculateRemainingCapital(Application application) {
        var capital = new Money(studio.getCash().getValue());
        capital = capital.subtract(application.getHireBonus());
        capital = capital.subtract(application.getHireAgentFee());
        return capital;
    }

    /**
     * Calculates the daily expenditure if you accept the {@link Application}.
     *
     * @param application is the application calculating with
     * @return the yearly Expenditure
     */
    private Money calculateDailyExpenditure(Application application) {
        var dailyExpenditure = new Money(new BigDecimal(0));
        for (var office : studio.getOffices()) {
            dailyExpenditure = dailyExpenditure.add(office.getLease());
            for (var developer : office.getDevelopers()) {
                dailyExpenditure = dailyExpenditure.add(developer.getSalary());
            }
        }

        dailyExpenditure = dailyExpenditure.add(application.getDeveloper().getSalary());
        return dailyExpenditure;
    }

    /**
     * Hires a {@link Developer} in a {@link Office}.
     *
     * @throws WrongChoiceException when the indexes are out of range.
     */
    public void hireApplicationDeveloper() throws WrongChoiceException, NotAvailableException {
        if (studio.getApplications() == null || studio.getApplications().size() <= 0) {
            throw new NotAvailableException("You can not hire a developer because there are no applications!");
        }
        var developerIndex = TextHandler.getInt("which one do you want to hire?", 1, studio.getApplications().size()) - 1;

        var outputText = new ArrayList<String>();
        for (var office : studio.getOffices()) {
            outputText.add(office.getName().getName());
        }
        TextHandler.print(outputText, "Offices", "Office", true, null);
        var officeIndex = TextHandler.getInt("In which office?", 1, studio.getOffices().size()) - 1;
        studio.acceptApplication(studio.getApplications().get(developerIndex), studio.getOffices().get(officeIndex));
        TextHandler.print("Hired developer " + studio.getApplications().get(developerIndex).getDeveloper().getName().getName() + " in office " + studio.getOffices().get(officeIndex).getName().getName());
        studio.setApplications(studio.getApplications().stream().filter(application -> application != studio.getApplications().get(developerIndex)).toList());
    }

    /**
     * Creates new {@link Application} and adds it to the {@link GameDevStudio}.
     */
    public void create() {
        TextHandler.print("An application will be created. Please enter the following values:");
        var name = TextHandler.getText("Developer name:");
        var skills = TextHandler.getSkillset();
        var hireBonus = TextHandler.getDouble("Hire bonus: [value between: 500 and 1000]", 500, 1000);
        var hireAgentFee = TextHandler.getDouble("Hire agent fee: [value between: 2000 and 2500]", 2000, 2500);
        var salary = TextHandler.getDouble("Salary: [value between: 3000 and 4000]", 3000, 4000);
        var applications = new ArrayList<>(studio.getApplications());
        applications.add(SpecialApplication.createSpecialApplication(skills, hireBonus, hireAgentFee, name, salary));
        studio.setApplications(applications);
        TextHandler.print("The application of " + name + " has been created successfully!");
        game.log("New application of " + name + " was created.");
    }
}

