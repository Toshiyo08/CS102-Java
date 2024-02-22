import java.util.*;

public class Deck {
    ArrayList<Card> cards = new ArrayList<Card>();
    private String[] suits = { "Diamond", "Clubs", "Hearts", "Spade" };
    private int[] numbers = { 2,3,4,5,6,7,8,9,10,11,12,13,14 };


    // initialising a deck
    public Deck() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 13; j++) {
                Card card = new Card(numbers[j],suits[i] );
                cards.add(card);
            }
        }

        shuffle();
    }

    // shuffle the deck
    public void shuffle() {

        // are we using array for cards or arraylist all the way?(to clarify)
        Collections.shuffle(cards);

    }

    public Card dealCard() {
        if (cards.isEmpty() == true) {
            throw new IllegalStateException("Deck is empty! Cannot deal more cards.");
        }
        //pop the first card from the arraylist
        return cards.remove(0);
    }

    //give players a number of cards(7 cards for texas hold em)
    public List<Card> dealCards(int numCards) {
        if (numCards > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck to deal.");
        }

        List<Card> dealtCards = new ArrayList<>();
        for (int i = 0; i < numCards; i++) {
            dealtCards.add(dealCard());
        }

        return dealtCards;
    }

    // return Card from deck based on index
    public Card getCard(int idx) {
        return cards.get(idx);
    }
}
