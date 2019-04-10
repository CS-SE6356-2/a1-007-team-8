package gofish;

class Player {
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
		return this.name;
	}

	public Hand getHand() {
		return this.hand;
	}

	public int getScore() {
		return this.score;
	}

	public int getWins() {
		return this.wins;
	}
}
