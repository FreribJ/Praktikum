package praktikum.special;

import gmbh.kdb.hsw.gdp.domain.*;

import java.math.BigDecimal;

/**
 * Represents a project created by the user.
 */
public class SpecialProject extends Project {

    /**
     * Same as {@link Project}.
     * @param name
     * @param effort
     * @param reward
     * @param customer
     * @param deadline
     */
    private SpecialProject(ProjectName name, Skillset effort, Money reward, CompanyName customer, Day deadline) {
        super(name, effort, reward, customer, deadline);
    }

    /**
     * Factory method of a {@link SpecialProject}.
     * @param name the name of the project.
     * @param skills the skills required by the project.
     * @param reward the reward the project brings.
     * @param customer the customer of the project.
     * @param deadline the deadline of the project.
     * @return a {@link SpecialProject} by the given params.
     */
    public static SpecialProject createSpecialProject(String name, Skillset skills, double reward, String customer, int deadline){
        var projectName = new ProjectName(name);
        var rewardMoney = new Money(new BigDecimal(reward));
        var customerName = new CompanyName(customer);
        var deadlineDay = new Day(deadline);
        return new SpecialProject(projectName, skills, rewardMoney, customerName, deadlineDay);
    }
}
