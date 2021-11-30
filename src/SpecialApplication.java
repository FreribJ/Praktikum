import gmbh.kdb.hsw.gdp.domain.Application;
import gmbh.kdb.hsw.gdp.domain.Developer;
import gmbh.kdb.hsw.gdp.domain.Money;

public class SpecialApplication extends Application {
    public SpecialApplication(Developer developer, Money hireBonus, Money hireAgentFee) {
        super(developer, hireBonus, hireAgentFee);
    }
}
