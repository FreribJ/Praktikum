package praktikum;

import org.w3c.dom.Text;
import praktikum.menu.MainMenu;

public class Main {
    /**
     * This is the praktikum.Main part of the program where it starts.
     * It prints out general information and starts by making a {@see praktikum.menu.MainMenu}
     */
    public static void main(String[] args) {
        TextHandler.print("""
                Java-Practicum by Daniel Koch,
                in cooperation with Finn, Jan, Jannes, Juliana, Magnus.
                """);
        TextHandler.print("""
                In this game, you are the CEO of your company.
                You want to survive as long as possible. You can manage your projects and applications.
                Furthermore, you can earn money by finishing projects.
                """);
        TextHandler.getText("Type anything to start");
        new MainMenu().run();
    }
}
