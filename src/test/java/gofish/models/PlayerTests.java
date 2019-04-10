package gofish.models;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTests {
	private Player player;

	@BeforeEach
	public void setUp() {
		player = new Player();
	}

	@Test
	public void testAddingToHand() {
		Card c = new Card("heart", "3");
		player.getHand().addCard(c);
		assertEquals(player.getHand().getPrivateCards().size(), 1);
		assertTrue(player.getHand().getPrivateCards().get(0).equals(c));
	}
}
