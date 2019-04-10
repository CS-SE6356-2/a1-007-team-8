package gofish.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;

public class CardCreationTest {
    // Test that card creation works and sets attributes to correct values
    @DisplayName ("Card Creation")
    @Test
    public void testCardCreation() {
        Card card = new Card("Club", "acE", 1);
        assertEquals(card.getSuit(), "club");
        assertEquals(card.getRank(), "ace");
        assertEquals(card.getValue(), 1);
    }

    // Test that the creation of an invalid card throws an exception
    @SuppressWarnings("unused")
    @DisplayName ("Invalid Card throws exception")
    @Test
    public void testCardInvalidError() {
        try {
            Card card = new Card("horse", "5", 5);
            fail("Invalid Card created.");
        } catch (IllegalArgumentException e) {}
        try {
            Card card = new Card("club", "asdf", 10);
            fail("Invalid Card created.");
        } catch (IllegalArgumentException e) {}
    }
}
