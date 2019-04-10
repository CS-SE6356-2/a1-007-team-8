<<<<<<< HEAD
package gofish.models;

public class Player {
=======
package gofish;

class Player {
>>>>>>> 99561a0a4cc2d2ab51c17e33ffa71204e6d01fe1
	private String name;
	private Hand hand;
	private int score, wins;

	public Player() {
		this.name = "Player";
		this.hand = new Hand();
		this.score = 0;
		this.wins = 0;
	}

	public Player(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Hand getHand() {
		return hand;
	}

	public int getScore() {
		return score;
	}

	public int getWins() {
		return wins;
	}

	// TODO: Make draw func
	/* public void draw(Frame f) {
		do something
	}*/
}
