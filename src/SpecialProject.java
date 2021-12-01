import gmbh.kdb.hsw.gdp.domain.*;

import java.math.BigDecimal;

public class SpecialProject extends Project {

    private SpecialProject(ProjectName name, Skillset effort, Money reward, CompanyName customer, Day deadline) {
        super(name, effort, reward, customer, deadline);
    }

    public static SpecialProject createSpecialProject(String name, Skillset skills, double reward, String customer, int deadline){
        var projectName = new ProjectName(name);
        var rewardMoney = new Money(new BigDecimal(reward));
        var customerName = new CompanyName(customer);
        var deadlineDay = new Day(deadline);
        return new SpecialProject(projectName, skills, rewardMoney, customerName, deadlineDay);
    }
}
