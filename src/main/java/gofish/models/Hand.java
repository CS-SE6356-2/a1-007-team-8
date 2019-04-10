package gofish.models;

import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class Hand {
    private ArrayList<Card> publicCards;
    private ArrayList<Card> privateCards;

    // Constructors

    public Hand() {
		publicCards = new ArrayList<Card>();
		privateCards = new ArrayList<Card>();
	}

    public Hand(ArrayList<Card> privateCards) {
		this();
        this.privateCards = privateCards;
    }

    public Hand(ArrayList<Card> privateCards, ArrayList<Card> publicCards) {
		this();
        this.privateCards = privateCards;
        this.publicCards = publicCards;
    }

    // Accessors

    public ArrayList<Card> getPublicCards() {
        return publicCards;
    }

    public ArrayList<Card> getPrivateCards() {
        return privateCards;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        cards.addAll(publicCards);
        cards.addAll(privateCards);
        return cards;
    }

    // Mutators

    /**
     * Removes a card from the hand
     * @param   card    the card to remove
     * @return          true upon successful removal, else false
     */
    public boolean removeCard(Card card) {
        if (privateCards.remove(card) || publicCards.remove(card))
            return true;
        return false;
    }

    /**
     * Add a card to the hand
     * @param   card        the card to add
     * @param   isPublic    true to denote card as public, false for private
     */
    public void addCard(Card card, boolean isPublic) {
        if (isPublic)
            publicCards.add(card);
        else
            addCard(card);
    }

    /**
     * Add a private card to the hand
     * @param   card    the card to add
     */
    public void addCard(Card card) {
        privateCards.add(card);
    }

    // TODO: Make draw func
    /* public void draw(Frame f) {
        do something
    }*/

    // Helpers

    /**
     * Checks to see if a card exists in the hand
     * @param   card    the card to check existance for
     * @return          true if card exists in hand, else false
     */
    public boolean hasCard(Card card) {
        return privateCards.contains(card) || publicCards.contains(card);
    }
}
