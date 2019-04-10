package gofish;
import java.util.ArrayList;

public class PlayArea {
	private ArrayList<Card> cards;

	public PlayArea() {
		this.cards = new ArrayList<Card>();
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public Card removeCard() {
		return removeCard(0);
	}
	
	public Card removeCard(int index) {
		try {
			return cards.remove(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean addCard(Card c) {
		return addCard(cards.size(), c);
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
