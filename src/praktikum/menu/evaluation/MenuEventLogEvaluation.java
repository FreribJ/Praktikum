package praktikum.menu.evaluation;

import gmbh.kdb.hsw.gdp.Game;
import praktikum.TextHandler;

import java.util.ArrayList;

/**
 * Handles the evaluation of the event log.
 */
public class MenuEventLogEvaluation {
    Game game;

    /**
     * Constructs a {@link MenuEventLogEvaluation}.
     * @param game the game operated with.
     */
    public MenuEventLogEvaluation(Game game) {
        this.game = game;
    }

    /**
     * Prints all events from the event log.
     */
    public void showEventLog() {
        var eventLog = new ArrayList<>(this.game.getEventLog());
        TextHandler.print(eventLog, "Number of Events: " + eventLog.size(), "Event");
    }
}

