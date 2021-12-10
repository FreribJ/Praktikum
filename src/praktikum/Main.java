package praktikum;

import org.w3c.dom.Text;
import praktikum.menu.MainMenu;

public class Main {
    /**
     * This is the praktikum.Main Part of the Program where its starts
     * Starts by making a {@see praktikum.menu.MainMenu}
     *
     */
    public static void main(String[] args) {
        TextHandler.print("""
                In this game, you are the CEO of your company.
                You want to survive as long as possible. You can manage your projects and applications.
                Furthermore, you can earn money by finishing projects.
                (!!! Create Methods are for testing purposes only !!!)
                """);
        TextHandler.getText("Type anything to Start");
        new MainMenu().run();
    }
}
