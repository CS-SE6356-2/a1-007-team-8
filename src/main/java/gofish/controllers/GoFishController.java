package gofish.controllers;

import gofish.models.Card;
import gofish.models.Player;

import java.util.HashMap;
import java.util.Map;

public class GoFishController extends GameController {
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

    public boolean hasBook(Player p) {
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
                return true;
            }
        }
        return false;
    }
}
