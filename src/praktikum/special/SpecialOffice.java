package praktikum.special;

import gmbh.kdb.hsw.gdp.domain.Money;
import gmbh.kdb.hsw.gdp.domain.Office;
import gmbh.kdb.hsw.gdp.domain.OfficeName;

import java.math.BigDecimal;

/**
 * Represents an office created by the user.
 */
public class SpecialOffice extends Office {

    /**
     * Same as {@link Office}.
     *
     * @param name
     * @param lease
     */
    private SpecialOffice(OfficeName name, Money lease) {
        super(name, lease);
    }

    /**
     * Factory method of an {@link Office}.
     *
     * @param name  the name of the office.
     * @param lease the lease of the office.
     * @return a {@link SpecialOffice} by the given params.
     */
    public static SpecialOffice createSpecialOffice(String name, double lease) {
        var developerName = new OfficeName(name);
        var leaseMoney = new Money(new BigDecimal(lease));
        return new SpecialOffice(developerName, leaseMoney);
    }
}
