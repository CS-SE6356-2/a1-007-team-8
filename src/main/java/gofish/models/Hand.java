package gofish.models;

import gofish.App;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Hand extends JComponent {
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

    public int getCardCount() {
        return publicCards.size() + privateCards.size();
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

    /**
     * Make an existing public card private
     * @param   card    public card to make private
     */
    public boolean makeCardPrivate(Card card) {
        // Make sure card exists in public cards
        if (!publicCards.contains(card))
            return false;
        publicCards.remove(card);
        addCard(card);
        return true;
    }

    /**
     * Make an existing private card public
     * @param   card    private card to make public
     */
    public boolean makeCardPublic(Card card) {
        // Make sure card exists in private cards
        if (!privateCards.contains(card))
            return false;
        privateCards.remove(card);
        addCard(card, true);
        return true;
    }

    /**
     * Removes all cards from the hand
     */
    public void empty() {
        privateCards.clear();
        publicCards.clear();
    }

    // Helpers

    /**
     * Checks to see if a card exists in the hand
     * @param   card    the card to check existance for
     * @return          true if card exists in hand, else false
     */
    public boolean hasCard(Card card) {
        return privateCards.contains(card) || publicCards.contains(card);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Hand) {
            return ((Hand) o).getPrivateCards().equals(privateCards) && ((Hand) o).getPublicCards().equals(publicCards);
        }
        return false;
    }

    @Override
    public void paint(Graphics g) {
        int deltaX = (App.WIDTH - Card.WIDTH) / Math.max(privateCards.size(), publicCards.size());
        int margin = App.WIDTH / Card.WIDTH;
        for (int i = 0; i < privateCards.size(); i++) {
            Card c = privateCards.get(i);
            c.setBounds(getX() + i * deltaX + Card.WIDTH / 4, getY() + Card.HEIGHT, Card.WIDTH, Card.HEIGHT);
            c.paint(g, true);
        }
        for (int i = 0; i < publicCards.size(); i++) {
            Card c = publicCards.get(i);
            c.setBounds(getX() + i * deltaX + Card.WIDTH / 4, getY(), Card.WIDTH, Card.HEIGHT);
            c.paint(g, true);
        }
    }
}
