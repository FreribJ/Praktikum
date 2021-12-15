package praktikum.menu;

import gmbh.kdb.hsw.gdp.Game;
import gmbh.kdb.hsw.gdp.domain.Office;
import praktikum.TextHandler;
import praktikum.special.SpecialOffice;

/**
 * Handles all the {@link Office} related operations.
 */
public class MenuOffice {
    Game game;

    /**
     * Constructor of a {@link MenuOffice}.
     * @param game the game operated with
     */
    public MenuOffice(Game game) {
        this.game = game;
    }

    /**
     * Creates a new {@link Office} and adds it to the office-list.
     */
    public void create(){
        TextHandler.print("An office will be created. Please enter the following values:");
        var name = TextHandler.getText("Name: ");
        var lease = TextHandler.getDouble("Lease: [value between: 250 and 1000]", 250, 1000);
        this.game.getStudio().getOffices().add(SpecialOffice.createSpecialOffice(name, lease));
        TextHandler.print("The office " + name + " has been created successfully!");
        this.game.log("New office " + name + " was created.");
    }
}
