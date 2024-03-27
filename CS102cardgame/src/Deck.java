
//package CS102cardgame.src;

import java.util.*;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
    }

    public void initializeDeck() {
        String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };
        // 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A
        int[] ranks = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
        for (String suit : suits) {
            for (int rank : ranks) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            // No more cards in the deck
            return null;
        }
        return cards.remove(0);
    }

    public Card burnCard() {
        cards.remove(0);
        return cards.remove(0);
    }

}
