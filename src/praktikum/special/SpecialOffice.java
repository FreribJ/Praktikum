package praktikum.special;

import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.OfficeName;

import java.math.BigDecimal;

public class SpecialOffice extends Office {

    private SpecialOffice(OfficeName name, Money lease) {
        super(name, lease);
    }

    public static SpecialOffice createSpecialOffice(String name, double lease) {
        var developerName = new OfficeName(name);
        var leaseMoney = new Money(new BigDecimal(lease));
        return new SpecialOffice(developerName, leaseMoney);
    }
}
