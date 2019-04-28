package gofish.models;

import gofish.App;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Card extends JComponent {
	static final String[] SUITS = {"clubs", "spades", "diamonds", "hearts"};
	static final String[] RANKS = {"ace", "king", "queen", "jack", "joker", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	public static final int WIDTH = 102;
	public static final int HEIGHT = 150;
    private int value;
    private String suit, rank;

    // Constructors

    public Card(String rank, String suit, int value) throws IllegalArgumentException {
        this(rank, suit);
        this.value = value;
        setBounds(getX(), getY(), WIDTH, HEIGHT);
    }

	public Card(String rank, String suit) throws IllegalArgumentException {
        // Check if requested card is valid
        if (!validateCard(suit.toLowerCase(), rank.toLowerCase())) {
            throw new IllegalArgumentException("Card suit or rank is invalid.");
        }
        this.suit = suit.toLowerCase();
        this.rank = rank.toLowerCase();
		this.value = defaultValue(rank);
		setBounds(getX(), getY(), WIDTH, HEIGHT);
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

	public void paint(Graphics g, boolean faceUp) {
    	int x = getX();
    	int y = getY();
		String fileName;
    	if (faceUp) {
		    fileName = (suit.charAt(0) + "").toUpperCase() + ".png";
		    if(!rank.equals("10")) {
			    fileName = (rank.charAt(0) + "").toUpperCase() + fileName;
		    } else {
			    fileName = "10" + fileName;
		    }
		    fileName = "cards/" + fileName;
	    } else {
			fileName = "cards/gray_back.png";
	    }

	    try {
		    final BufferedImage image = ImageIO.read(new File(ClassLoader.getSystemClassLoader().getResource(fileName).toURI()));
		    g.drawImage(image, x, y, null);
	    } catch (IOException | URISyntaxException e) {
	    	App.log("Failed to load card resource (" + fileName + ")");
	    	e.printStackTrace();
	    }
	}
}
