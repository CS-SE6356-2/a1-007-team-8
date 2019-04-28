package gofish.models;

import gofish.App;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Card extends JComponent {
	static final String[] SUITS = {"clubs", "spades", "diamonds", "hearts"};
	static final String[] RANKS = {"ace", "king", "queen", "jack", "joker", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	static final int WIDTH = 100;
	static final int HEIGHT = 140;
    private int value;
    private String suit, rank;

    // Constructors

    public Card(String rank, String suit, int value) throws IllegalArgumentException {
        this(rank, suit);
        this.value = value;
    }

	public Card(String rank, String suit) throws IllegalArgumentException {
        // Check if requested card is valid
        if (!validateCard(suit.toLowerCase(), rank.toLowerCase())) {
            throw new IllegalArgumentException("Card suit or rank is invalid.");
        }
        this.suit = suit.toLowerCase();
        this.rank = rank.toLowerCase();
		this.value = defaultValue(rank);
	}

    // Accessors

    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    // TODO: Make draw func
    /* public void draw(Frame f) {
        do something
    }*/

    // Helpers

    /**
     * Validates that a card's rank/suit exists
     * @param   suit    suit of requested card
     * @param   rank    rank of requested card
     * @return          true of requested suit and rank are valid
     */
    private static boolean validateCard(String suit, String rank) {
        // Check if suit/rank is invalid
        if (!Arrays.stream(SUITS).anyMatch(suit::equals) || !Arrays.stream(RANKS).anyMatch(rank::equals))
            return false;
        return true;
    }

	private static int defaultValue(String rank) {
		try {
			return Integer.parseInt(rank);
		} catch (NumberFormatException e) {
			switch (rank) {
				case "jack": return 11;
				case "queen": return 12;
				case "king": return 13;
				case "ace": return 1;
				default: return -1;
			}
		}
	}

    @Override
	public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Card))
            return false;
        Card other = (Card) o;
		return this.rank.equals(other.getRank()) && this.suit.equals(other.getSuit()) && this.value == other.getValue();
	}

	@Override
	public void paint(Graphics g) {
    	int x = getX();
    	int y = getY();
    	App.log("painting");
    	g.setColor(new Color(0xFFFFFF));
    	g.fillRect(x, y, WIDTH, HEIGHT);
	}
}
