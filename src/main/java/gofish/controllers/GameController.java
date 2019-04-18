package gofish.controllers;

import gofish.models.*;
import java.util.*;

public class GameController {
	private Game game;
	private int turnNumber, roundNumber;
	private Rule[] rules;

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

	public boolean addPlayer(Player p) {
		game.getPlayers().add(p);
		if (!checkRule("maxPlayers")) {
			removePlayer(); // Remove most recently added player
			return false;
		}
		return true;
	}

	public Player removePlayer() {
		return removePlayer(game.getPlayers().size() - 1);
	}

	public boolean removePlayer(Player p) {
		return game.getPlayers().remove(p);
	}

	public Player removePlayer(int index) {
		try {
			return game.getPlayers().remove(index);
		} catch	(IndexOutOfBoundsException e) {
			return null;
		}
	}

	public void deal() {
		for (Player p : game.getPlayers()) {
			deal(p);
		}
	}

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

	public Player checkWinner() {
		int winningScore = getRuleValue("pointsToWin");
		for (Player p : game.getPlayers()) {
			if (p.getScore() >= winningScore) {
				return p;
			}
		}
		return null;
	}

	private boolean checkRule(String ruleName) {
		for (Rule r : rules) {
			if (r.getName().equals(ruleName)) {
				return r.isMet(game);
			}
		}
		return true; // If no matching rule is found, assume that it is ok
	}

	private boolean checkRules() {
		for (Rule r : rules) {
			if (!r.isMet(game)) {
				return false;
			}
		}
		return true;
	}

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

	private ArrayList<String> getBrokenRules() {
		ArrayList<String> brokenRules = new ArrayList<String>();
		for (Rule r : rules) {
			if (!r.isMet(game)) {
				brokenRules.add(r.getName());
			}
		}
		return brokenRules;
	}
}
