package gofish.models;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayAreaTests {
	private PlayArea playArea;

	@BeforeEach
	public void setUp() {
		playArea = new PlayArea();
	}
	
	@Test
	public void testPlayAreaStartsEmpty() {
		assertEquals(playArea.getCards().size(), 0);
	}

	@Test
	public void testAddingCards() {
		Card toAdd = new Card("3", "clubs");
		playArea.addCard(toAdd);
		assertTrue(playArea.getCards().contains(toAdd));
	}

	@Test
	public void testRemovingCards() {
		Card toAdd = new Card("3", "clubs");
		playArea.addCard(toAdd);
		playArea.removeCard();
		assertFalse(playArea.getCards().contains(toAdd));
	}
}
