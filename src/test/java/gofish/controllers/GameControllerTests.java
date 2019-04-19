package gofish.controllers;

import gofish.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTests {
    private GameController gc;
    private Game game;
    // Basic Go Fish rules
    private Rule[] rules = new Rule[]{ new Rule("maxPlayers", 7, (Game game, int value) -> game.getPlayers().size() <= value ),
                                       new Rule("maxHandSize", 52, (Game game, int value) -> {
                                           for (Player p : game.getPlayers()) {
                                               if (p.getHand().getCardCount() > value) {
                                                   return false;
                                               }
                                           }
                                           return true;
                                       }),
                                       new Rule("dealCardCount", (Game game) -> {
                                           int nPlayers = game.getPlayers().size();
                                           if (nPlayers == 2 || nPlayers == 3) {
                                               return 7;
                                           } else if (nPlayers >= 4) {
                                               return 5;
                                           } else {
                                               return -1;
                                           }
                                       }),
                                       new Rule("pointsToWin", 100)
                                     };

    // Start game with one deck and five players
    @BeforeEach
    public void setUp() {
        game = new Game("Go Fish");
        game.getDecks().add(new Deck());
        game.getPlayers().add(new Player("Player 1"));
        game.getPlayers().add(new Player("Player 2"));
        game.getPlayers().add(new Player("Player 3"));
        game.getPlayers().add(new Player("Player 4"));
        game.getPlayers().add(new Player("Player 5"));
        gc = new GameController(game, rules);
    }

    // Test if winner checking works
    @Test
    public void testWinnerChecking() {
        assertNull(gc.checkWinner());
        game.getPlayers().get(0).setScore(200);
        assertNotNull(gc.checkWinner());
    }

    // Test if advancing turns works
    @Test
    public void testAdvanceTurn() {
        ArrayList<Player> players = game.getPlayers();
        game.setActivePlayer(players.get(0));
        for (int i = 0; i < players.size(); i++) {
            assertEquals(players.get(i), game.getActivePlayer());
            assertEquals(gc.advanceTurn(), players.get((i + 1) % players.size()));
        }
        players.get(0).setScore(200);
        gc.advanceTurn();
        assertNotNull(gc.checkWinner());
        assertEquals(gc.checkWinner().getWins(), 1);
    }

    // Test if max player checking works
    @Test
    public void testMaxPlayers() {
        game.getPlayers().clear();
        for (int i = 0; i < 7; i++) {
            assertTrue(gc.addPlayer(new Player()));
        }
        assertFalse(gc.addPlayer(new Player()));
    }

    // Test if player removal works
    @Test
    public void testPlayerRemoval() {
        for (int i = 0; i < 5; i++) {
            assertNotNull(gc.removePlayer());
        }
        assertNull(gc.removePlayer());
    }

    // Test if dealing to players works
    @Test
    public void testDealing() {
        for (Player p : game.getPlayers()) {
            assertEquals(p.getHand().getCardCount(), 0);
        }
        gc.deal();
        for (Player p : game.getPlayers()) {
            assertEquals(rules[2].getValue(game), p.getHand().getCardCount());
        }
    }

    // Test all rules
    @Test
    public void testRules() {
        for (Rule r : rules) {
            assertTrue(r.isMet(game));
        }

        game.getPlayers().clear();
        for (int i = 0; i < 8; i++) {
            game.getPlayers().add(new Player());
        }
        assertFalse(rules[0].isMet(game));

        for (int i = 0; i < 53; i++) {
            game.getPlayers().get(0).getHand().addCard(new Card("spade", "ace"));
        }
        assertFalse(rules[1].isMet(game));
    }
}
