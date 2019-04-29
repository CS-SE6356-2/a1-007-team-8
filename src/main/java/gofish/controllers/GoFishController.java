package gofish.controllers;

import gofish.App;
import gofish.models.Card;
import gofish.models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoFishController extends GameController {

    public GoFishController(String name, Rule[] rules) {
        super(name, rules);
    }

    @Override
    public Player checkWinner() {
        Player maxScorePlayer = null;
        int maxScore = -1;
        int totalBooks = 0;
        for (Player p : game.getPlayers()) {
            totalBooks += p.getScore();
            if (p.getScore() > maxScore) {
                maxScore = p.getScore();
                maxScorePlayer = p;
            }
        }
        if (totalBooks == 13) {
            return maxScorePlayer;
        }
        return null;
    }

    public boolean checkMatch(Player p, String rank) {
        for (Card c : p.getHand().getPrivateCards()) {
            if (c.getRank().equals(rank)) {
                return true;
            }
        }
        return false;
    }

    public Card hasBook(Player p) {
        Map<String, Integer> ranks = new HashMap<>();
        for (Card c : p.getHand().getPrivateCards()) {
            String rank = c.getRank();
            if (ranks.containsKey(rank)) {
                ranks.put(rank, ranks.get(rank) + 1);
            } else {
                ranks.put(rank, 1);
            }
        }
        for (String key : ranks.keySet()) {
            if (ranks.get(key) >= 4) {
                return new Card(key, "spades");
            }
        }
        return null;
    }

    // Checks if the requestee has a card matching the rank of the requested card
    // If they do --> move all matching cards into requester's hand and return the matching cards
    // Else --> return return empty array list
    public ArrayList<Card> requestCard(Player requester, Player requestee, Card c) {
        ArrayList<Card> matchedCards = new ArrayList<>();
        App.log("Request Card: "+c.getRank());
        for (int i = 0; i < requestee.getHand().getPrivateCards().size(); i++) {
            Card rc = requestee.getHand().getPrivateCards().get(i);
            App.log(rc.getRank());
            if (rc.getRank().equals(c.getRank())) {
                matchedCards.add(rc);
                requestee.getHand().removeCard(rc);
                requester.getHand().addCard(rc, false);
                i--;
            }
        }
        return matchedCards;
    }
}
