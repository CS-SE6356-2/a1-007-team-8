package gofish;
import java.util.Arrays;

class Card {
	static final String[] SUITS = {"club", "spade", "diamond", "heart"};
	static final String[] RANKS = {"ace", "king", "queen", "jack", "joker", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private int value;
    private String suit, rank;

    // Constructors

    public Card(String suit, String rank, int value) throws IllegalArgumentException {
        this(suit, rank);
        this.value = value;
    }

	public Card(String suit, String rank) throws IllegalArgumentException {
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
}
