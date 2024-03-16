// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Hand2 { /* If no Turn or River, value taken in parameter is 0 or B */
    public static int getHandValue(ArrayList<Card> playerHand, ArrayList<Card> tableCommCards) {
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
        System.out.println(handTable);

        int copy[] = new int[15];
        for (int i = 0; i < 14; i++) {
            copy[i] = 0;
        }
        for (Card o : handTable) {
            copy[o.getRank()]++;
        }

        int score = isRSF(handTable, copy);
        if (score != 0) {
            System.out.println("Royal Straight Flush");
            return score;
        } else if (isSF(handTable, copy) != 0) {
            score = isSF(handTable, copy);
            System.out.println("Straight Flush");
            return score;
        } else if (is4Kind(copy) != 0) {
            score = is4Kind(copy);
            System.out.println("4 of a kind");
            return score;
        } else if (isFH(copy) != 0) {
            score = isFH(copy);
            System.out.println("Full House");
            return score;
        } else if (isF(handTable) != 0) {
            score = isF(handTable);
            System.out.println("Flush");
            return score;
        } else if (str8(copy) != 0) {
            score = str8(copy);
            System.out.println("Straight");
            return score;
        } else if (is3Kind(copy) != 0) {
            score = is3Kind(copy);
            System.out.println("3 of a kind");
            return score;
        } else if (is2Pair(copy) != 0) {
            score = is2Pair(copy);
            System.out.println("2 pairs");
            return score;
        } else if (isPair(copy) != 0) {
            score = isPair(copy);
            System.out.println("Pair");
            return score;
        } else if (highCard(copy) != 0) {
            score = highCard(copy);
            System.out.println("High Card");
            return score;
        }

        return score;
    }

    public static boolean evalcomplete(ArrayList<Card> handTable) {
        return false;
    }

    public static int isRSF(ArrayList<Card> handTable, int[] copy) {
        int counter = 0;
        Boolean isAce5 = false;
        if (copy[14] >= 1) {
            for (int i = 14; i > 9; i--) {
                if (copy[i] >= 1) {
                    counter++;
                }
                if (counter == 5) {
                    isAce5 = true;
                    break;
                }
            }
        }
        
        String[] suitSample = {"Diamonds", "Clubs", "Hearts", "Spades"};
        for (int i = 0; i < 4; i++) {
            int num = 14;
            for (Card o : handTable) {
                if (o.getRank() == num && o.getSuit().equals(suitSample[i])) {
                    counter++;
                    num --;
                    if (num == 9) {
                        return 10;
                    }
                } 
            }
        }

        return 0;
    }

    public static int isSF(ArrayList<Card> handTable, int[] copy) {
        String[] suitSample = {"Diamonds", "Clubs", "Hearts", "Spades"};
        for (int i = 0; i < 4; i++) {
            for (Card o : handTable) {
                int num = o.getRank();
                int original = num;
                for (Card e : handTable) {
                    if (e.getRank() == num && e.getSuit().equals(suitSample[i])) {
                        num --;
                        if (num == original - 5) {
                            return 9;
                        }
                    } 
                }
                
            }
        }

        return 0;
    }

    public static int is4Kind(int[] copy) {
        Boolean quad = false;

        for (int i = 14; i > 0; i--) {
            if (copy[i] == 4) {
                quad = true;
                return 8;
            }
        }

        return 0;
    }

    public static int isFH(int[] copy) {
        Boolean triple = false;
        Boolean pair = false;

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14

            if (copy[i] == 3) {
                triple = true;
            } else if (copy[i] == 2) {
                pair = true;
            }
        }
        if (triple && pair) {
            return 7;
        }
        return 0;
    }

    public static int isF(ArrayList<Card> handTable) {
        for (int i = 0; i < handTable.size(); i++) {
            String holder = handTable.get(i).getSuit();
            int counter = 0;
            for (int j = i; j < handTable.size(); j++) {
                if (handTable.get(j).getSuit().equals(holder)) {
                    counter++;
                }
                if (counter == 5) {
                    return 6;
                }
            }
            counter = 0;
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
                return 5;
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
                return 4;
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
                if (i == 2) {
                    return 0;
                }
                for (int j = i - 1; j > 0; j--) {
                    if (copy[j] == 2) {
                        pair2 = true;
                        return 3;
                    }
                }
                // return 3;
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
                // return 14 + i;
                return 2;
            }
        }

        return 0;
    }

    public static int highCard(int[] copy) {

        for (int i = 14; i > 0; i--) {
            // 0 1 2 3 4 5 6 7 8 9 10 11 12
            // 2 3 4 5 6 7 8 9 10 11 12 13 14
            if (copy[i] == 1) {

                // return 14;
                return 1;
            }
        }

        // return 2;
        return 0;
    }
}
