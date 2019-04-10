package gofish.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;

public class HandTest {
    private Hand hand = new Hand();

    // Populate hand with 5 public and private cards
    @BeforeAll
    public void populateHand() {
        for (int i = 0; i < 5; i++) {
            Card c = new Card("heart", Integer.toString(i), i);
            Card c1 = new Card("spade", Integer.toString(i), i);
            hand.addCard(c);
            hand.addCard(c1, true);
        }
    }

    // getCards() should be the same as getPublicCards() and getPrivateCards() combined
    @Test
    public void testHandGetCards() {
        ArrayList<Card> allCards = new ArrayList<>();
        allCards.addAll(hand.getPrivateCards());
        allCards.addAll(hand.getPublicCards());
        assertEquals(hand.getCards(), allCards);
    }


}