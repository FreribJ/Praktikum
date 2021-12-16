package praktikum.special;

import gmbh.kdb.hsw.gdp.domain.*;

import java.math.BigDecimal;

/**
 * Represents an application created by the user.
 */
public class SpecialApplication extends Application {

    /**
     * Same as {@link Application}
     *
     * @param developer    the developer for the application
     * @param hireBonus    the hire bonus for the application
     * @param hireAgentFee the hire agent fee for the application
     */
    private SpecialApplication(Developer developer, Money hireBonus, Money hireAgentFee) {
        super(developer, hireBonus, hireAgentFee);
    }

    /**
     * Factory method for an SpecialApplication of a {@link Developer}.
     *
     * @param skills       the skills the developer has.
     * @param hireBonus    the hire bonus for the developer.
     * @param hireAgentFee the hire agent fee of the application.
     * @param name         the name of the developer.
     * @param salary       the salary for the developer.
     * @return a {@link SpecialApplication} by the given params.
     */
    public static SpecialApplication createSpecialApplication(Skillset skills, double hireBonus, double hireAgentFee, String name, double salary) {
        var developer = new Developer(new DeveloperName(name), new Money(new BigDecimal(salary)), Happiness.create(), null, new Day(0), skills);
        var hireBonusMoney = new Money(new BigDecimal(hireBonus));
        var hireAgentFeeMoney = new Money(new BigDecimal(hireAgentFee));
        return new SpecialApplication(developer, hireBonusMoney, hireAgentFeeMoney);
    }
}
