package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.GameDevStudio;
import praktikum.TextHandler;
import praktikum.special.SpecialOffice;

public class MenuOffice {
    public void create(Game game){
        TextHandler.print("An office will be created. Please enter the following values:");
        var name = TextHandler.getText("Name: ");
        var lease = TextHandler.getDouble("Lease: ");
        game.getStudio().getOffices().add(SpecialOffice.createSpecialOffice(name, lease));
        TextHandler.print("The office " + name + " has been created successfully!");
        game.log("New office " + name + " was created.");
    }
}
