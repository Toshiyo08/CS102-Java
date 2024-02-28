
//package CS102cardgame.src;



public class Card {
    private final String suit;
    private final int rank;
    // Diamond
    // Heart
    // Spades
    // Club
    public Card(String cardsuit, int cardrank) {
        this.suit = cardsuit;
        this.rank = cardrank;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    // Not required
    // public int compareTo(Card o) {
    //     if (this.rank == o.rank) {
    //         return 0;
    //     } else if (this.rank > o.rank) {
    //         return 1;
    //     } else {
    //         return -1;
    //     }
    // }
    
    public String toString() {
        String rankname = null;
        switch (rank) {
            case 2:
                rankname = "Two";
                break;
            case 3:
                rankname = "Three";
                break;
            case 4:
                rankname = "Four";
                break;
            case 5:
                rankname = "Five";
                break;
            case 6:
                rankname = "Six";
                break;
            case 7:
                rankname = "Seven";
                break;
            case 8:
                rankname = "Eight";
                break;
            case 9:
                rankname = "Nine";
                break;
            case 10:
                rankname = "Ten";
                break;
            case 11: // Jack
                rankname = "Jack";
                break;
            case 12: // Queen
                rankname = "Queen";
                break;
            case 13: // King
                rankname = "King";
                break;
            case 14: //Ace
                rankname = "Ace";
                break;
        
            default: //can remove?
                rankname = "Joker";
                break;
        }

        return rankname + " of " + suit;
    }
    
}