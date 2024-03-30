package Eval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Base.*;

 // Royal Straight Flush 13 AKQJ10, same suit
        // Straight Flush 12 56789, same suit
        // 4 kind 11 '7777'Q
        // Full House (3:2) 10 '999'KK'(3:2)
        // Flush 8 Same suit
        // Straight 7 '56789'
        // 3 kind 6 '333'45
        // 2 pairs 4 'AA88'J --> A and 8 are the pairs
        // 1 pair 2 '665K2 --> 6 is the pair
        // High Card 1 2846'A' --> A is the highest card

//This class is used to evaluate the hand of the player and the table community cards

public class HandEval { /* If no Turn or River, value taken in parameter is 0 or B */
    public static int getHandValue(ArrayList<Card> playerHand, ArrayList<Card> tableCommCards) {
       
        // Create an array for the function to refer
        ArrayList<Card> handTable = new ArrayList<Card>();
        for (int i = 0; i < playerHand.size(); i++) {
            handTable.add(playerHand.get(i));
        }
        for (int i = 0; i < tableCommCards.size(); i++) {
            handTable.add(tableCommCards.get(i));
        }
        Collections.sort(handTable, Comparator.comparing(Card::getRank).reversed());

        int copy[] = new int[15];
        for (int i = 0; i < 14; i++) {
            copy[i] = 0;
        }
        for (Card o : handTable) {
            copy[o.getRank()]++;
        }

        int score = isRSF(handTable); 
        if (score != 0) {
            return score;                           // 422-425
        } else if (isSF(handTable) != 0) {
            score = isSF(handTable);
            return score;                           // 386-421
        } else if (is4Kind(copy) != 0) {
            score = is4Kind(copy);
            return score;                           // 318-366
        } else if (isFH(copy) != 0) {
            score = isFH(copy);
            return score;                           // 254-310
        } else if (isF(handTable, copy) != 0) {
            score = isF(handTable, copy);
            return score;                           // 202-236
        } else if (isStraight(copy) != 0) {
            score = isStraight(copy);
            return score;                           // 160-200
        } else if (is3Kind(copy) != 0) {
            score = is3Kind(copy);
            return score;                           // 104-140
        } else if (is2Pair(copy) != 0) {
            score = is2Pair(copy);
            return score;                           // 52-98
        } else if (isPair(copy) != 0) {
            score = isPair(copy);
            return score;                           // 18-42
        } else if (isHighCard(copy) != 0) {
            score = isHighCard(copy);                 // 2-14
            return score;
        }

        return score;
    }

    public static int isRSF(ArrayList<Card> handTable) { //Royal Straight FLush
        String[] suitSample = { "Diamonds", "Clubs", "Hearts", "Spades" };
        for (int i = 0; i < 4; i++) {
            //num is ace
            int num = 14;
            //loops thru cards in hand 
            for (Card o : handTable) {
                //checks if it is ace and if the cards after the ace have the same suit as ace
                if (o.getRank() == num && o.getSuit().equals(suitSample[i])) {
                    //to find the matching suit(check for next card)
                    num--;
                    //found RSF
                    if (num == 9) {
                        return 425;
                    }
                }
            }
        }

        return 0;
    }

    public static int isSF(ArrayList<Card> handTable) { //Straight Flush

        String[] suitSample = { "Diamonds", "Clubs", "Hearts", "Spades" };
        for (int i = 0; i < 4; i++) {
            for (Card o : handTable) {
                //gets the highest card of the straight
                int num = o.getRank();
                //stores highest card of the straight
                int original = num;
                int sum = num;
                for (Card e : handTable) {
                    if (e.getRank() == num && e.getSuit().equals(suitSample[i])) {
                        num--;
                        sum += num;
                        if (num == original - 5) {
                            return 366 + sum;
                        }
                    }
                }
            }
        }
        
        return 0;
    }

    public static int is4Kind(int[] copy) { //4 of a kind

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 4) {
                return 310 + i * 4;
            }
        }

        return 0;
    }

    public static int isFH(int[] copy) { //Full House
        boolean triple = false;
        boolean pair = false;
        int sum = 0;

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 3) {
                triple = true;
                sum += i;
            } else if (copy[i] == 2) {
                pair = true;
                sum += i;
            }
        }
        if (triple && pair) {
            return 242 + sum;
        }
        return 0;
    }

    public static int isF(ArrayList<Card> handTable, int[] copy) {//isFlush
        for (int i = 0; i < handTable.size(); i++) {
            String suitHolder = handTable.get(i).getSuit();
            //count number of cards with the same suit
            int sameSuitCounter = 0;
            for (int j = i; j < handTable.size(); j++) {
                if (handTable.get(j).getSuit().equals(suitHolder)) {
                    sameSuitCounter++;
                }
                if (sameSuitCounter == 5) {
                    if (is3Kind(copy) != 0) {
                        return 200 + is3Kind(copy);
                    } else if (is2Pair(copy) != 0) {
                        return 200 + is2Pair(copy);
                    } else if (isPair(copy) != 0) {
                        return 200 + isPair(copy);
                    } else {
                        return 200 + isHighCard(copy);
                    }
                }
            }
            sameSuitCounter = 0;
        }

        return 0;
    }

    public static int isStraight(int[] copy) {
        int consecutiveCounter = 0;
        int sum = 0;
        for (int i = 14; i > 0; i--) {
            if (copy[i] >= 1) {
                consecutiveCounter++;
                sum += i;
            } else {
                consecutiveCounter = 0;
                sum = 0;
            }
            if (consecutiveCounter == 5) {
                return 140 + sum;
            }
        }

        return 0;
    }

    public static int is3Kind(int[] copy) {

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 3) {
                return 98 + i * 3;
            }
        }

        return 0;
    }

    public static int is2Pair(int[] copy) {

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 2) {
                //i represents rank
                if (i == 2) {
                    //if pair is 2, we are at the end of the loop so no more possibility for 2 pairs
                    return 0;
                }
                for (int j = i - 1; j > 0; j--) {
                    if (copy[j] == 2) {
                        return 42 + i * 2 + j * 2;
                    }
                }
            }
        }

        return 0;
    }

    public static int isPair(int[] copy) {

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 2) {
                return 14 + i * 2;
            }
        }

        return 0;
    }

    public static int isHighCard(int[] copy) {

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 1) {
                return i;
            }
        }
        return 0;
    }

    public static double calculateChenScore(List<Card> hand) {
        Card card1 = hand.get(0);
        Card card2 = hand.get(1);
        int rank1 = card1.getRank();
        String suit1 = card1.getSuit();
        int rank2 = card2.getRank();
        String suit2 = card2.getSuit();
        int highRank = Math.max(rank1, rank2);
        int lowRank = Math.min(rank1, rank2);
        int rankDiff = highRank - lowRank;
        int gap = (rankDiff > 1) ? rankDiff - 1 : 0;
        boolean isPair = (rank1 == rank2);
        boolean isSuited = (suit1.equals(suit2));

        double score = 0;

        // 1. Base score highest rank only
        // Ace
        if (highRank == 14) {
            score = 10.0;
        } else if (highRank == 13) {
            score = 8.0;
        } else if (highRank == 12) {
            score = 7.0;
        } else if (highRank == 11) {
            score = 6.0;
        } else {
            score = (highRank + 2) / 2.0;
        }

        // 2. If pair, double score, with minimum score of 5.
        if (isPair) {
            score *= 2.0;
            if (score < 5.0) {
                score = 5.0;
            }
        }

        // 3. If suited, add 2 points.
        if (isSuited) {
            score += 2.0;
        }

        // 4. Subtract points for gap.
        if (gap == 1) {
            score -= 1.0;
        } else if (gap == 2) {
            score -= 2.0;
        } else if (gap == 3) {
            score -= 4.0;
        } else if (gap > 3) {
            score -= 5.0;
        }

        // 5. Add 1 point for a 0 or 1 gap and both cards lower than a Queen.
        if (!isPair && gap < 2 && rank1 < 12 && rank2 < 12) {
            score += 1.0;
        }

        // Minimum score is 0.
        if (score < 0.0) {
            score = 0.0;
        }

        // 6. Round half point scores up.
        return score;
    }
}
