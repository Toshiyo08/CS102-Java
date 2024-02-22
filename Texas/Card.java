// package CS102cardgame.src;

import java.util.Comparator;

public class Card {
    private int number;
    private String suit;

    public Card(int number, String suit) {
        this.suit = suit;
        this.number = number;
    }
    public Card(){
        
    }

    public int getNumber() {
        return this.number;
    }

    public String getSuit() {
        return this.suit;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String toString() {
        String name = "";

        if (this.number > 10) {
            if (this.number == 11) {
                name += "Jack";
            } else if (this.number == 12) {
                name += "Queen";
            } else if (this.number == 13) {
                name += "King";
            } else if (this.number == 14) {
                name += "Ace";
            }
        } else {
            name += this.number;
        }
        name += " of " + this.suit;

        return name;
    }

    // Everything below is a Comparator copied from a different source
    class rankComparator implements Comparator<Object> {
        public int compare(Object card1, Object card2) throws ClassCastException {
            // verify two Card objects are passed in
            if (!((card1 instanceof Card) && (card2 instanceof Card))) {
                throw new ClassCastException("A Card object was expeected.  Parameter 1 class: " + card1.getClass()
                        + " Parameter 2 class: " + card2.getClass());
            }

            int rank1 = ((Card) card1).getNumber();
            int rank2 = ((Card) card2).getNumber();

            return rank1 - rank2;
        }
    }

    class suitComparator implements Comparator<Object> {
        public int compare(Object card1, Object card2) throws ClassCastException {
            // verify two Card objects are passed in
            if (!((card1 instanceof Card) && (card2 instanceof Card))) {
                throw new ClassCastException("A Card object was expeected.  Parameter 1 class: " + card1.getClass()
                        + " Parameter 2 class: " + card2.getClass());
            }

            // short suit1 = ((Card) card1).getSuit();
            // short suit2 = ((Card) card2).getSuit();

            if (((Card) card1).getSuit().equals(((Card) card2).getSuit())){
                return 1;
            }
            return 0;
        }
    }
}