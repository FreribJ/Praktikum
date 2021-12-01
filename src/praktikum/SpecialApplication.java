package praktikum;

import gmbh.kdb.hsw.gdp.domain.*;

import java.math.BigDecimal;

public class SpecialApplication extends Application {

    private SpecialApplication(Developer developer, Money hireBonus, Money hireAgentFee) {
        super(developer, hireBonus, hireAgentFee);
    }

    public static SpecialApplication createSpecialApplication(Skillset skills, double hireBonus, double hireAgentFee, String name, double salary){
        var developer = new Developer(new DeveloperName(name), new Money(new BigDecimal(0)), Happiness.create(), null, new Day(0), skills);
        var hireBonusMoney = new Money(new BigDecimal(hireBonus));
        var hireAgentFeeMoney = new Money(new BigDecimal(hireAgentFee));
        return new SpecialApplication(developer, hireBonusMoney, hireAgentFeeMoney);
    }
}
