package gofish.controllers;

import gofish.models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import gofish.views.*;

import javax.swing.*;

public class GameController {
	protected Game game;
	protected int turnNumber, roundNumber;
	protected Rule[] rules;
	protected Frame frame;
	private static boolean verbose = false;

	/* Game Controller */

	public GameController() {
		turnNumber = 0;
		roundNumber = 0;
		rules = new Rule[]{};
		game = new Game();
	}

	public GameController(Game game, Rule[] rules) {
		this();
		this.game = game;
		this.rules = rules;
	}

	public GameController(String name, Rule[] rules) {
		this();
		this.game = new Game(name);
		this.rules = rules;
	}

	/**
	 * Moves the game state to the next turn, checking for rule violations and a winner
	 * @return The player who is going next when advanceTurn is called, or null if a winner is found
	 */
	public Player advanceTurn() {
		Player winner = checkWinner();
		if (winner != null) {
			game.setActivePlayer(null);
			winner.setWins(winner.getWins() + 1);
			return null;
		}
		turnNumber++;
		ArrayList<Player> players = game.getPlayers();
		Player nextPlayer = players.get(turnNumber % players.size());
		game.setActivePlayer(nextPlayer);
		return nextPlayer;
	}

	/**
	 * Add a player
	 * @param p The player to add
	 * @return true if successfully added, false otherwise
	 */
	public boolean addPlayer(Player p) {
		game.getPlayers().add(p);
		if (!checkRule("maxPlayers")) {
			removePlayer(); // Remove most recently added player
			return false;
		}
		return true;
	}

	/**
	 * Remove the last player
	 * @return The player removed
	 */
	public Player removePlayer() {
		return removePlayer(game.getPlayers().size() - 1);
	}

	/**
	 * Remove specific player
	 * @param p The player to remove
	 * @return true if successfully removed, false otherwise
	 */
	public boolean removePlayer(Player p) {
		return game.getPlayers().remove(p);
	}

	/**
	 * Remove player by index
	 * @param index index of player to remove
	 * @return the player removed, or null if index is out of bounds
	 */
	public Player removePlayer(int index) {
		try {
			return game.getPlayers().remove(index);
		} catch	(IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Deal to all players
	 */
	public void deal() {
		for (Player p : game.getPlayers()) {
			deal(p);
		}
	}

	/**
	 * Deal to specific player
	 * @param p Player to deal to
	 */
	public void deal(Player p) {
		Deck dealingDeck = game.getDecks().get(0);
		int dealCardCount = getRuleValue("dealCardCount");
		for (int i = 0; i < dealCardCount; i++) {
			Card c = dealingDeck.drawCard();
			p.getHand().addCard(c);
			if (!checkRule("maxHandSize")) {
				p.getHand().removeCard(c);
				break;
			}
		}
	}

	/**
	 * Check if game has a winner based on the pointsToWin rule
	 * @return The winner if one found, null otherwise
	 */
	public Player checkWinner() {
		int winningScore = getRuleValue("pointsToWin");
		for (Player p : game.getPlayers()) {
			if (p.getScore() >= winningScore) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Check if rule conditions are met
	 * @param ruleName the name of the rule being tested
	 * @return true or false depending on if the test passed
	 */
	private boolean checkRule(String ruleName) {
		for (Rule r : rules) {
			if (r.getName().equals(ruleName)) {
				return r.isMet(game);
			}
		}
		return true; // If no matching rule is found, assume that it is ok
	}

	/**
	 * Check if all rule conditions are met
	 * @return true if all rule conditions are met, false otherwise
	 */
	private boolean checkRules() {
		for (Rule r : rules) {
			if (!r.isMet(game)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Get the value associated with a rule name
	 * @param ruleName the name of the rule that you want the value for
	 * @return The value of the rule with name ruleName
	 */
	private int getRuleValue(String ruleName) {
		for (Rule r : rules) {
			if (r.getName().equals(ruleName)) {
				if (r.getValueCalculator() == null) {
					return r.getValue();
				} else {
					return r.getValue(game);
				}
			}
		}
		return -1;
	}

	/**
	 * Get the names of the rules that are violated
	 * @return An arraylist of the names of the rules that are violated
	 */
	private ArrayList<String> getBrokenRules() {
		ArrayList<String> brokenRules = new ArrayList<String>();
		for (Rule r : rules) {
			if (!r.isMet(game)) {
				brokenRules.add(r.getName());
			}
		}
		return brokenRules;
	}

	public Frame getFrame() { return frame; }

	public void setFrame(Frame frame) { this.frame = frame; }
}
