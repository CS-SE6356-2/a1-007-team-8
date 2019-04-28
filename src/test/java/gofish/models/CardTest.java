package gofish.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    private final String SUIT = "spades";
    private final String RANK = "6";
    private final int VALUE = 6;
    private Card card = new Card(RANK, SUIT, VALUE);

    // Test that getSuit() returns the correct value
    @Test
    public void testCardGetSuit() {
        assertEquals(card.getSuit(), SUIT);
    }

    // Test that getRank() returns the correct value
    @Test
    public void testCardGetRank() {
        assertEquals(card.getRank(), RANK);
    }

    // Test that getValue() returns the correct value
    public void testCardGetValue() {
        assertEquals(card.getValue(), VALUE);
    }
}
