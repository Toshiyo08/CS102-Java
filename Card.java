// package CS102cardgame.src;

public class Card {
    private int number;
    private String suit;

    public Card(int number, String suit) {
        this.suit = suit;
        this.number = number;
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
}