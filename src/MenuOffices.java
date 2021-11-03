import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import gmbh.kdb.hsw.gdp.domain.Office;

public class MenuOffices {
    public static void showOffices(GameDevStudio studio){
        System.out.println("Numbers of offices: " + studio.getOffices().size());
        for (Office office : studio.getOffices()) {
            printOffice(office);
        }
    }
    private static void printOffice(Office office){
        System.out.println("Name: " + office.getName().getName());
        System.out.println("Lease: " + office.getLease().toString());
        System.out.println("Number of Developer: " + office.getDevelopers().size());
    }
}
