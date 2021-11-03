import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

public class MenuOffices {
    public static void showOffices(GameDevStudio studio){
        String offices = "";
        for (Office office : studio.getOffices()) {
            offices.concat(printOffice(office));
        }
        System.out.println(offices);
    }
    private static String printOffice(Office office){
        return
        office.getName() +
        office.getLease().toString() +
        office.getDevelopers().size();
    }
}
