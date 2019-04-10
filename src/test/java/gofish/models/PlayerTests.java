package gofish;

import org.junit.Test;
import org.junit.Before;
import junit.framework.TestCase;
import static org.junit.Assert.*;

public class PlayerTests extends TestCase {
	private Player player;

	@Before
	public void setUp() {
		player = new Player();
	}

	@Test
	public void testAddingToHand() {
		Card c = new Card("hearts", "3");
		player.getHand().addCard(c);
		assertEquals(player.getHand().getPrivateCards().size(), 1);
		assertTrue(player.getHand().getPrivateCards().get(0).equals(c));
	}
}
