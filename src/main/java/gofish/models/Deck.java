package gofish;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private ArrayList<Card> cards;

	public Deck() {
		this.cards = new ArrayList<Card>();
		addCards();
		shuffle();
	}

	private void addCards() {
		for (String suit : Card.SUITS) {
			for (String rank : Card.RANKS) {
				cards.add(new Card(suit, rank));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public ArrayList<Card> getCards() {
		return cards;
	}

	public Card drawCard() {
		// Draw from top
		return cards.remove(0);
	}

	public Card drawCard(int index) {
		try {
			return cards.remove(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	public boolean addCard(Card c) {
		// Add to top
		return addCard(0, c);
	}

	public boolean addCard(int index, Card c) {
		try {
			cards.add(index, c);
			return true;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}
}
