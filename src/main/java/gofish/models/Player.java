package gofish.models;

public class Player {
	private String name;
	private Hand hand;
	private int score, wins, id;

	public Player() {
		this.name = "Player";
		this.hand = new Hand();
		this.score = 0;
		this.wins = 0;
		this.id = -1;
	}

	public Player(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Player) {
			return ((Player) o).getHand().equals(hand) && ((Player) o).getName().equals(name) && ((Player) o).getScore() == score && ((Player) o).getWins() == wins && ((Player) o).getId() == id;
		}
		return false;
	}

	// TODO: Make draw func
	/* public void draw(Frame f) {
		do something
	}*/
}
