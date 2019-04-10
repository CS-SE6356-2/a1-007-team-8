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
		removeCard(0);
	}
	
	public Card removeCard(int index) {
		try {
			return cards.remove(index);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean addCard(Card c) {
		return cards.add(cards.size(), c);
	}
	
	public boolean addCard(int index, Card c) {
		return cards.add(index, c);
	}
}
