//package CS102cardgame.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand {                        /*If no Turn or River, value taken in parameter is 0 or B */
    public int getHandValue(ArrayList<Card> playerHand, ArrayList<Card> tableCommCards) {
        // Royal Straight Flush 13      AKQJ10, same suit
        // Straight Flush       12      56789, same suit
        // 4 kind               11      '7777'Q
        // Full House (3:2)     10      '999'KK'(3:2)
        // Flush                8       Same suit
        // Straight             7       '56789'
        // 3 kind               6       '333'45
        // 2 pairs              4       'AA88'J
        // 1 pair               2       '66'5K2
        // High Card            1       2846'A'

        // Check suits first. 
        // If duplicate suit = 5
            // RSFlush, SFlush, Flush
                // RSF: Check for Ace, else
                // SF: Check for consecutive ranks else
                // F: Assign points


        // Check ranks
        // If consecutive rank = 5
            // Straight
        // If duplicate rank = 4
            // 4 kind
        // If duplicate rank = 3
            // Fhouse, 3 kind
                // FH: Check other 2 is pair, else
                // 3k: Assign points
        // If duplicate rank = 2
            // 2 pair, 1 pair
                // 2p: Check other 3 for pair, else
                // 1p: Assign points
        // High card :(
        
        // Create an array for the function to refer
        ArrayList<Card> handTable = new ArrayList<Card>();
        for (int i = 0; i < playerHand.size(); i++) {
            handTable.add(playerHand.get(i));
        }
        for (int i = 0; i < tableCommCards.size(); i++) {
            handTable.add(tableCommCards.get(i));
        }

        // A K K Q J 10 8
        
// Go from more strict to less strict. conditions MUST be like (if straight && flush)
//                                                        THEN (if straight) OR (if flush)
        // If consecutive:
                // Check duplicate suits = 5
                    // If 5 duplicates: 
                        // Check Ace
                            // If Ace:
                                // RSF points
                            // If no Ace:
                                // SF points
                    // If <5 duplicates:
                        // S points
            // If not consecutive:
                // Check duplicate ranks:
                    // 4 duplicate = 4 kind + high card
                    // 3 duplicate = FH (3kind priority)
                    // 3 duplicate = triple + high card
                    // 2 duplicate = 2 pair + high card
                    // 2 duplicate = 1 pair + high card
                    // 1 duplicate = high card :(
        

        while (!evalcomplete(handTable)) {
            // Sort the hand+table in order of ranking
            Collections.sort(handTable, Comparator.comparing(Card::getRank).reversed());

            // Check for consecutive rank
            if (isStraight(handTable)){
                // Check duplicate suits = 5
                if (isSameSuit(handTable)){
                    
                    // Check Ace
                    if (isAce(handTable)){
                        return RSF_POINTS;
                    } else {
                        return SF_POINTS;
                    }
                } else {
                    return S_POINTS;
                }
            } else {
                if (isSameSuit(handTable)) {
                    return F_POINTS;
                } else if (getNumDuplicate(handTable) == 4) {
                    return FOUR_KIND_POINTS;
                } else if (getNumDuplicate(handTable) == 3) {
                    if (isFH(handTable)) {
                        return FH_POINTS;
                    } else {
                        return THREE_KIND_POINTS;
                    }
                } else if (getNumDuplicate(handTable) == 2) {
                    if (isTwoPair(handTable)) {
                        return TWO_PAIR_POINTS;
                    } else {
                        return PAIR_POINTS;
                    }
                } else {
                    return HIGH_CARD_POINTS;
                }
            }
        }

        return 0;
    }

    public static boolean evalcomplete(ArrayList<Card> handTable) {
        return false;
    }

    public static boolean isStraight(ArrayList<Card> handTable){
        for (int i = 1; i < handTable.size(); i++) {
            if (handTable.get(i).getRank() > handTable.get(i - 1).getRank()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSameSuit(ArrayList<Card> handTable){
        String rank1 = handTable.get(0).getSuit();
        for (int i = 1; i < handTable.size(); i++) {
            if (!((handTable.get(i).getSuit()).equals(rank1))){
                return false;
            }
        }
        return true;
    }

    public static boolean isAce(ArrayList<Card> handTable){
        // A A K K Q J 10 
        for (int i = 0; i < )
        if (handTable.get(0).getRank() == 14){
            return true;
        }
        return false;
    }

    public static int getNumDuplicate(ArrayList<Card> handTable){
        int num = 1;
        // K, Q, 8, 8, 7, 7, 2
        //    Q, 8, 8, 7, 7, 2
        for (int i = 0; i < handTable.size(); i++) {
            for (int j = 0; j < handTable.size(); j++){
                if ((handTable.get(i).getSuit()).equals((handTable.get(j).getSuit()))) {
                    num++;
                }
            }
        }
        return 0;
    }

    public static boolean isFH(ArrayList<Card> handTable){
        return false;
    }

    public static boolean isTwoPair(ArrayList<Card> handTable){
        return false;
    }

    
}
