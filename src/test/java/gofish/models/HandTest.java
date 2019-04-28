package gofish.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.ParameterizedTest;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;

public class HandTest {
    private static Hand hand = new Hand();

    // Populate hand with 5 public and private cards
    @BeforeEach
    public void populateHand() {
        hand.empty();
        for (int i = 2; i < 7; i++) {
            Card c = new Card(Integer.toString(i), "hearts");
            Card c1 = new Card(Integer.toString(i), "spades");
            hand.addCard(c);
            hand.addCard(c1, true);
        }
    }

    // getCards() should be the same as getPublicCards() and getPrivateCards() combined
    @Test
    public void testHandGetCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(hand.getPublicCards());
        allCards.addAll(hand.getPrivateCards());
        assertEquals(hand.getCards(), allCards);
    }

    // getCardCount() should return same int as size of getCards()
    @Test
    public void testHandGetCardCount() {
        assertEquals(hand.getCardCount(), hand.getCards().size());
    }

    // Has should return true for existing card and false for non-existing
    @ParameterizedTest
    @CsvSource({
        "hearts, 6,  true",
        "hearts, 10, false",
        "spades, 2,  true",
        "clubs,  2,  false"
    })
    public void testHandHasCard(String suit, String rank, boolean expectedResult) {
        Card card = new Card(rank, suit);
        assertEquals(hand.hasCard(card), expectedResult);
    }

    // Card count should increase when a card is added
    @Test
    public void testHandIncrementCardCout() {
        int count = hand.getCardCount();
        hand.addCard(new Card("10", "clubs"));
        assertEquals(hand.getCardCount(), ++count);
    }

    // Card should not exist in hand after removal
    @Test
    public void testHandRemoveCard() {
        Card card = new Card("5", "hearts");
        assertEquals(hand.hasCard(card), true);
        hand.removeCard(card);
        assertEquals(hand.hasCard(card), false);
    }

    // Card should exist in hand after adding
    @Test
    public void testHandAddCard() {
        Card card = new Card("5", "diamonds");
        assertEquals(hand.hasCard(card), false);
        hand.addCard(card);
        assertEquals(hand.hasCard(card), true);
    }

    // Test makeCardPublic()
    @Test
    public void testHandMakePublic() {
        Card card = new Card("4", "hearts");
        assertEquals(hand.getPrivateCards().contains(card), true);
        hand.makeCardPublic(card);
        assertEquals(hand.getPrivateCards().contains(card), false);
        assertEquals(hand.getPublicCards().contains(card), true);
    }

    // Test makeCardPrivate()
    @Test
    public void testHandMakePrivate() {
        Card card = new Card("4", "spades");
        assertEquals(hand.getPublicCards().contains(card), true);
        hand.makeCardPrivate(card);
        assertEquals(hand.getPublicCards().contains(card), false);
        assertEquals(hand.getPrivateCards().contains(card), true);
    }

    // Test empty()
    @Test
    public void testHandEmpty() {
        assertEquals(hand.getCardCount() > 0, true);
        hand.empty();
        assertEquals(hand.getCardCount() == 0, true);
    }
}