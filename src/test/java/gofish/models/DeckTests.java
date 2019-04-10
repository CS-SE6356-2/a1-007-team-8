package gofish;

import org.junit.Test;
import org.junit.Before;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class DeckTests extends TestCase {
	private Deck deck;

	@Before
	public void setUp() {
		deck = new Deck();
	}

	@Test
	public void testNewDeckHasRightSize() {
		assertEquals(deck.getCards().size(), 52);
	}

	@Test
	// Has a 1 / 52! chance to produce a false negative
	public void testShuffle() {
		ArrayList<Card> oldDeck = new ArrayList<Card>(deck.getCards()); // Create a shallow copy
		deck.shuffle();
		assertFalse(oldDeck.equals(deck.getCards()));
	}

	@Test
	public void testDrawingCard() {
		Card drawn = deck.drawCard();
		assertFalse(deck.getCards().contains(drawn));
		drawn = deck.drawCard(20);
		assertFalse(deck.getCards().contains(drawn));
	}

	@Test
	public void testAddingCard() {
		Card toAdd = new Card("heart", "jack");
		while (deck.getCards().contains(toAdd)) {
			deck.getCards().remove(toAdd);
		}
		deck.addCard(toAdd);
		assertTrue(deck.getCards().contains(toAdd));

		deck.getCards().remove(toAdd);
		deck.addCard(20, toAdd);
		assertTrue(deck.getCards().contains(toAdd));
	}
}
