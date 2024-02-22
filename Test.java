public class Test {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        for (int i = 0; i < 52; i++) {
            Card currentCard = deck.getCard(i);
            System.out.println(currentCard.getValue() + " of " + currentCard.getSuit());
        }
        
    }
}

