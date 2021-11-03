import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

public class menuOffices {
    public static String showOffices(GameDevStudio studio){
        String offices = "";
        for (Office office : studio.getOffices()) {
            offices.concat(printOffice(office));
        }
        return offices;
    }
    private static String printOffice(Office office){
        return
        office.getName() +
        office.getLease().toString() +
        office.getDevelopers().size();
    }
}
