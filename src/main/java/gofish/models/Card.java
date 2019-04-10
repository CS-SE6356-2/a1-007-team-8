package gofish;

import java.util.Arrays;

class Card {
    int value;
    String suit, rank;

    // Constructor

    public Card(String suit, String rank, int value) throws IllegalArgumentException {
        // Check if requested card is valid
        if (!validateCard(suit.toLowerCase(), rank.toLowerCase())) {
            throw new IllegalArgumentException("Card suit or rank is invalid.");
        }
        this.suit = suit.toLowerCase();
        this.rank = rank.toLowerCase();
        this.value = value;
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
    private boolean validateCard(String suit, String rank) {
        final String[] SUITS = {"club", "spade", "diamond", "heart"};
        final String[] RANKS = {"ace", "king", "queen", "jack", "joker", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        // Check if suit/rank is invalid
        if (!Arrays.stream(SUITS).anyMatch(suit::equals) || !Arrays.stream(RANKS).anyMatch(rank::equals))
            return false;
        return true;
    }
}