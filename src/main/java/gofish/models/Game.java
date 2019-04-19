package gofish.models;

import gofish.controllers.Rule;
import java.util.ArrayList;

public class Game {
	private ArrayList<Deck> decks;
	private ArrayList<Player> players;
	private Player activePlayer;
	private PlayArea playArea;
	private String name;

	public Game() {
		this.decks = new ArrayList<Deck>();
		this.players = new ArrayList<Player>();
		this.activePlayer = null;
		this.playArea = new PlayArea();
		this.name = "";
	}

	public Game(String name) {
		this();
		this.name = name;
	}

	public ArrayList<Deck> getDecks() {
		return decks;
	}

	public void setDecks(ArrayList<Deck> decks) {
		this.decks = decks;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public Player getActivePlayer() {
		return activePlayer;
	}

	public void setActivePlayer(Player player) {
		this.activePlayer = player;
	}

	public PlayArea getPlayArea() {
		return playArea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
