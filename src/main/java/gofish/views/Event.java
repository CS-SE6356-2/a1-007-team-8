package gofish.views;

import gofish.controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Event implements ActionListener {
    private GameController gc;
    private EventHandler handler;

    /**
     * Called when action on component triggered
     * @param e The event describing what happened
     */
    public void actionPerformed(ActionEvent e) {
        handler.handle(e, gc);
    }

    /**
     * Manipulates the GameController based on the ActionEvent received
     */
    private interface EventHandler {
        void handle(ActionEvent e, GameController gc);
    }

    public Event(GameController gc, EventHandler handler) {
        this.gc = gc;
        this.handler = handler;
    }
}
