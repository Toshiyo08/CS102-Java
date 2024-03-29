package Cards;

import java.util.ArrayList;

public class Card {
    private final String suit;
    private final int rank;

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

    public static void printCard(ArrayList<Card> handTable) {
        for (int i = 0; i < handTable.size(); i++) {
            System.out.print(" ┌─────────┐ ");
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            if (handTable.get(i).getRank() <= 9) {
                System.out.printf(" |%d        | ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 10) {
                System.out.printf(" |%d       | ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |J        | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |Q        | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |K        | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |A        | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // ------------First Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 4 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |       _ | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |       ^ | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |       W | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // -----------Second Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 2 && handTable.get(i).getRank() <= 6) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() >= 8 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" |   ___<O | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |   ___<O>| ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" |   ___<O | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // --------------Third Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() == 3 || handTable.get(i).getRank() == 5 || handTable.get(i).getRank() == 7
                    || handTable.get(i).getRank() == 9 || handTable.get(i).getRank() == 14) {
                System.out.print(" |    " + (char) s + "    | ");
            } else if (handTable.get(i).getRank() == 2 || handTable.get(i).getRank() == 4
                    || handTable.get(i).getRank() == 8) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() == 6 || handTable.get(i).getRank() == 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11 || handTable.get(i).getRank() == 12
                    || handTable.get(i).getRank() == 13) {
                System.out.print(" |  / " + (char) s + "  | | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) { // ---------------4th Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() >= 2 && handTable.get(i).getRank() <= 6) {
                System.out.print(" |         | ");
            } else if (handTable.get(i).getRank() >= 8 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() >= 11 && handTable.get(i).getRank() <= 13) {
                System.out.print(" |  |____/ | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            } else {
                System.out.print(" |    " + (char) s + "    | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {// ---------Last Row
            int s = 0;
            if (handTable.get(i).getSuit().equals("Hearts")) {
                s = 3;
            } else if (handTable.get(i).getSuit().equals("Diamonds")) {
                s = 4;
            } else if (handTable.get(i).getSuit().equals("Clubs")) {
                s = 5;
            } else if (handTable.get(i).getSuit().equals("Spades")) {
                s = 6;
            }

            if (handTable.get(i).getRank() == 2 || handTable.get(i).getRank() == 3) {
                System.out.print(" |    " + (char) s + "    | ");
            } else if (handTable.get(i).getRank() >= 4 && handTable.get(i).getRank() <= 10) {
                System.out.print(" |  " + (char) s + "   " + (char) s + "  | ");
            } else if (handTable.get(i).getRank() == 11) {
                System.out.print(" | O>      | ");
            } else if (handTable.get(i).getRank() == 12) {
                System.out.print(" |<O>      | ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.print(" | O>      | ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.print(" |         | ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            if (handTable.get(i).getRank() <= 9) {
                System.out.printf(" |        %d| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 10) {
                System.out.printf(" |       %d| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 11) {
                System.out.printf(" |        J| ", handTable.get(i).getRank());
            } else if (handTable.get(i).getRank() == 12) {
                System.out.printf(" | V      Q| ");
            } else if (handTable.get(i).getRank() == 13) {
                System.out.printf(" | M      K| ");
            } else if (handTable.get(i).getRank() == 14) {
                System.out.printf(" |        A| ");
            }
        }
        System.out.println();
        for (int i = 0; i < handTable.size(); i++) {
            System.out.print(" └─────────┘ ");
        }
        System.out.println();

    }
    

}
    // ♥♦♣♠
    // ┌─────────┐ ┌─────────┐ 
    // |2        | |3        | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |         | |    ♥    | 
    // |        2| |        3| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |4        | |5        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |         | |         | 
    // |         | |    ♥    | 
    // |         | |         | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        4| |        5| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |6        | |7        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |    ♥    | |  ♥   ♥  | 
    // |         | |    ♥    | 
    // |    ♥    | |         | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        6| |        7| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |8        | |9        | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |         | |    ♥    | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |  ♥   ♥  | |  ♥   ♥  | 
    // |        8| |        9| 
    // └─────────┘ └─────────┘ 
    // ┌─────────┐ ┌─────────┐ 
    // |10       | |J        | 
    // |  ♥   ♥  | |       ▲ | ▲ ▼
    // |  ♥   ♥  | |   ___<O | 
    // |  ♥   ♥  | |  /    | | 
    // |  ♥   ♥  | |  |____/ | 
    // |  ♥   ♥  | | O>      | 
    // |       10| | ▼      J| 
    // └─────────┘ └─────────┘   // Dune Cards??
    // ┌─────────┐ ┌─────────┐  |  ┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐ 
    // |Q        | |K        |  |  |J        | |Q        | |K        | |A        | 
    // |       ^ | |       W |  |  |       ^ | |       W | |       ^ | |         | 
    // |   ___<O>| |   ___<O |  |  |   ___<O>| |   ___<O | |   ___<O>| |         | 
    // |  /    | | |  /    | |  |  |  /    | | |  /    | | |  /    | | |    ♥    | 
    // |  |____/ | |  |____/ |  |  |  |____/ | |  |____/ | |  |____/ | |         | 
    // |<O>      | | O>      |  |  |<O>      | | O>      | |<O>      | |         | 
    // | V      Q| | M      K|  |  | V      J| | M      Q| | V      K| |        A| 
    // └─────────┘ └─────────┘  |  └─────────┘ └─────────┘ └─────────┘ └─────────┘
    // ┌─────────┐ ┌─────────┐ 
    // |A        | |J        | 
    // |         | |         | 
    // |         | |  ♥   ♥  | 
    // |    ♥    | |         | 
    // |         | |  ♥   ♥  | 
    // |         | |         | 
    // |        A| |        J| 
    // └─────────┘ └─────────┘ 
    //