package gofish.views;

import gofish.controllers.GameController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventListener implements ActionListener {
   // private static GameController gc;
    private EventHandler handler;

    /**
     * Called when action on component triggered
     * @param e The event describing what happened
     */
    public void actionPerformed(ActionEvent e) {
        handler.handle(e);
    }

    /**
     * Manipulates the GameController based on the ActionEvent received
     */
    public interface EventHandler {
        void handle(ActionEvent e);
    }

    public EventListener(EventHandler handler) {
        //gc = gameController;
        this.handler = handler;
    }
}
