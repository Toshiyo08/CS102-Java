// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand2 { /* If no Turn or River, value taken in parameter is 0 or B */
    public int getHandValue(ArrayList<Card> playerHand, ArrayList<Card> tableCommCards) {
        // Royal Straight Flush 13 AKQJ10, same suit
        // Straight Flush 12 56789, same suit
        // 4 kind 11 '7777'Q
        // Full House (3:2) 10 '999'KK'(3:2)
        // Flush 8 Same suit
        // Straight 7 '56789'
        // 3 kind 6 '333'45
        // 2 pairs 4 'AA88'J
        // 1 pair 2 '66'5K2
        // High Card 1 2846'A'

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

        while (!evalcomplete(handTable)) {
            // Sort the hand+table in order of ranking

            // For each card, check array first
            isRSF(handTable); // Returns RSF if ace, SF if no
            isSF(handTable); // Returns SF if yes, 0 if no
            is4Kind(copy); // Returns 4kind if yes, 0 if no
            // Loop through array to find 4 cards of same rank
            isFH(copy); // Returns FH points if yes, 0 if no ||||| checks then remove 3 kind, checks
                        // then remove 2 kind
            // Loop through array to find 3 cards of same rank
            // Remove those card into a separate array, if don't have, return 0
            // Loop through remaining 4 cards to find 2 cards of same rank, if don't have
            // return 0
            // Remove those cards into array from above

            // Loop through remaining 2 cards to find pair, if have, assign point, if not,
            // high card
            flush(copy); // Returns F if yes, 0 if no
            // Loop through array to find 5 cards of same suit
            str8(copy); // Returns S if yes, 0 if no
            is3Kind(copy); // Returns 3kind if yes, 0 if no
            // Loop through array to find
            is2Pair(copy); // Returns 2pair if yes, 0 if no
            isPair(copy); // Returns pair if yes, highCard() if no
            highCard(copy); // Returns highest rank card
        }
        return 0;
    }

    public static boolean evalcomplete(ArrayList<Card> handTable) {
        return false;
    }

    public static int isRSF(int[] copy) {
        int counter = 0;
        if (copy[14] >=1) {
            for (int i = 14; i > 9; i++) {
                if (copy[i] >= 1){
                    counter++;
                }
                if (counter == 5) {
                    return ;
                }
            }
            
        }
        return 0;
    }

    public static int isSF(int[] copy) {
        Boolean isflushed = false;
        Boolean isStr8 = false;
        for (int i = 14; i > 0; i--) {
            String holder = getEvalSuit(copy[i]);
            int counter = 1;
            if (getEvalSuit(copy[i]).equals(holder)) {
                for (int j = i; j > 0; j--) {
                    counter++;
                    if (counter == 5) {
                        isflushed = true;
                        break;
                    }
                }
                if (isflushed) {
                    break;
                }
            }
        }

        int counter = 0;
        for (int i = 14; i > 0; i--) {
            if (copy[i] >= 1) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 5) {
                isStr8 = true;
                break;
            }
        }

        if (isflushed && isStr8) {
            return ;
        }

        return 0;
    }

    public static int is4Kind(int[] copy) {
        Boolean quad = false;

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 4) {
                quad = true;
                return 70 + i;
            }
        }

        return 0;
    }

    public static int isFH(int[] copy) {
        Boolean triple = false;
        Boolean pair = false;
        int counter = 0;

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            counter++;
            if (copy[i] == 3) {
                triple = true;
            } else if (copy[i] == 2) {
                pair = true;
            }
        }
        if (triple && pair) {
            return;
        }
        return 0;
    }

    public static int isF(int[] copy) {
        for (int i = 14; i > 0; i--) {
            String holder = getEvalSuit(copy[i]);
            int counter = 1;
            if (getEvalSuit(copy[i]).equals(holder)) {
                for (int j = i; j > 0; j--) {
                    counter++;
                    if (counter == 5) {
                        return;
                    }
                }
            }
        }

        return 0;
    }

    public static int str8(int[] copy) {
        int counter = 0;
        for (int i = 14; i > 0; i--) {
            if (copy[i] >= 1) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == 5) {
                return;
            }
        }

        return 0;
    }

    public static int is3Kind(int[] copy) {
        Boolean triple = false;

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            if (copy[i] == 3) {
                triple = true;
                return 42 + i;
            }
        }

        return 0;
    }

    public static int is2Pair(int[] copy) {
        Boolean pair1 = false;
        Boolean pair2 = false;

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            if (copy[i] == 2) {
                pair1 = true;
                for (int j = i; j > 0; j--) {
                    if (copy[j] == 2) {
                        pair2 = true;
                        return 28 + i;
                    }
                }
                return 0;
            }
        }

        return 0;
    }

    public static int isPair(int[] copy) {
        Boolean pair1 = false;

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            if (copy[i] == 2) {
                pair1 = true;
                return 14 + i;
            }
        }

        return 0;
    }

    public static int highCard(int[] copy) {

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            if (copy[i] == 1) {

                return 14;
            }
        }

        return 2;
    }
}
