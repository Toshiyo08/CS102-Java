// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand2 {                        /*If no Turn or River, value taken in parameter is 0 or B */
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
            // 1 Check RSF: Find 5 consecutive && same suit, put into new array and check Ace
            // 2 Check SF: Find 5 consecutive && same suit
            // 6 Check S: Find 5 consecutive
            // 5 Check F: Find 5 flush
            // 3 Check 4 Kind
            // 7 Check 3 kind
            // 4 Check FH
            // 8 Check 2 pairs
            // 9 Check pair
            // 10 Check High Card

            str8Suit(handTable); // Returns array of 5 SF hand, or null if no
            isRoyal(str8Suit(handTable)); // Returns RSF if ace, SF if no
            str8F(handTable); // Returns SF if yes, 0 if no
            is4Kind(handTable); // Returns 4kind if yes, 0 if no
            isFH(handTable); // Returns FH if yes, 0 if no ||||| checks then remove 3 kind, checks then remove 2 kind
            flush(handTable); // Returns F if yes, 0 if no
            str8(handTable); // Returns S if yes, 0 if no
            is3Kind(handTable); // Returns 3kind if yes, 0 if no
            is2Pair(handTable); // Returns 2pair if yes, 0 if no
            isPair(handTable); // Returns pair if yes, highCard() if no
            highCard(handTable); // Returns highest rank card
        }
        return 0;
    }

    public static boolean evalcomplete(ArrayList<Card> handTable) {
        return false;
    }

    // A-H A-H K-H K-H Q-H J-H 10-H: RSF
    // 4-S 4-S 4-S J-S J-S K-D K-D: FH 444KK or F444JJ? Answer: FH
    // 9-H 8-H 7-C 6-C 5-C 3-C 2-C: Str8:98765 or F:76532? Answer: Str8
        public static ArrayList<Card> str8Suit(ArrayList<Card> handTable) {
        ArrayList<Card> evaledHandTable = new ArrayList<Card>();
        for (int i = 0; i < handTable.size(); i++) {

            if (handTable.get(i).getRank() == handTable.get(i + 1).getRank()) {
                
            }

            evaledHandTable.add(handTable.get(i));
        }
        return evaledHandTable;
    }

    // Change
}
