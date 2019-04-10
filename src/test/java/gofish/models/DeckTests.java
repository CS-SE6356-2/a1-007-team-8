package gofish.models;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class DeckTests {
	private Deck deck;

	@BeforeEach
	public void setUp() {
		deck = new Deck();
	}

	@Test
	public void testNewDeckHasRightSize() {
		System.out.println(deck.getCards().size());
		assertEquals(52, deck.getCards().size());
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
