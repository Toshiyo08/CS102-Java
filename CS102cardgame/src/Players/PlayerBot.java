package Players;
// // package CS102cardgame.src;

import java.util.*;

import Base.*;
import Eval.*;

public class PlayerBot extends Player{
    /** Tightness (0 = loose, 100 = tight). */
    private int tightness;

    public PlayerBot(String name, String type, int tightness) {
        super(name, type);
        this.tightness = tightness;
    }

    public int getTightness(){
        return tightness;
    }

    public static int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1) {
        // botThinking(o.getName()); // AESTHETICS
        ArrayList<Card> hand = o.getHand();
        ArrayList<Card> commCards = table1.getCommCards();

        int handValue = HandEval.getHandValue(hand, commCards);
        double chenScore = calculateChenScore(hand);

        PlayerBot pb = (PlayerBot) o;
        int botTightness = pb.getTightness();
        double chenScoreToPlay = botTightness / 5.0;
        

        if (previousAction == null) {
            previousAction = "Check";
        }
        int increaseBetTo = getBotRaiseAmt(botTightness, handValue, o);
        
            // 2-14 HC
            // 18-42 pair
            //52-98 2pair
            // 7 - (14+7x2)
            // <7 bad 
            // >28 good
        int callAmt = table1.getCurrentBetAmt() - pb.getBet();
        if (afterBet1) {// fold if raise too high for given hand value
            if ((handValue) <= 28 && (handValue > 7)) { // ok hand
                //consider if bot need to call
                if (o.getBet() < table1.getCurrentBetAmt()) {
                    if (callAmt > ((100 - botTightness)/100) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) { 
                    // risky bet by bot determined by tightness and randomness
                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness/10) {
                        System.out.println("increaseBetTo: " + increaseBetTo);
                        System.out.println("Risky bet");
                        return 2;
                    }
                }
                
                if (o.getBet() == table1.getCurrentBetAmt()) {
                    return 1;
                }
                return 1;
            } else if (handValue > 28) {// if handValue > 28 = if better than a pair of 2 good hand
                
                if (callAmt > 0.9 * o.getBalance()) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if T.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) { // prospective remaining balance < remaining balance limit
                        return 1;
                    }
                    System.out.println("increaseBetTo: " + increaseBetTo);
                    return 2;
                }
            } else { // badhand
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    System.out.println("i should check");
                    return 1;
                } else {
                    
                    if (callAmt > ((100 - botTightness)/100) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
            }
            return 1;
        } else {
            if (chenScore < chenScoreToPlay) { // Bad Hand
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    return 1;
                } else {
                    
                    if (callAmt > ((100 - botTightness)/100) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
            } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0) { // Bot has good hadn enuf to bet
                
                if (callAmt > 0.9 * o.getBalance()) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if T.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) { // prospective remaining balance < remaining balance limit
                        return 1;
                    }
                    System.out.println("increaseBetTo: " + increaseBetTo);
                    return 2;
                }
            } else { // Ok hand
                
                if (o.getBet() < table1.getCurrentBetAmt()) {
                    if (callAmt > ((100 - botTightness)/100) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) {
                    
                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness%10) {
                        System.out.println("increaseBetTo: " + increaseBetTo);
                        System.out.println("Cheeky bet");
                        return 2;
                    }
                }
                
                if (o.getBet() == table1.getCurrentBetAmt()) {
                    return 1;
                }
                return 1;
            } 
            return 1;
        }
    }


    public static int getBotRaiseAmt(int tightness, int handValue, Player o) { // If betting, minimally a pair
        double bettingAmount = 300.0 * ((100.0 - tightness)/100.0) * (handValue/425.0) + (0.15*o.getBalance());
        return (int)bettingAmount;
    }

    public static void botThinking(String name) {
        System.out.print(name + ": ");
        Random random = new Random();
        int randomint = random.nextInt(6) + 1;
        try {
            Thread.sleep(100); // Wait for 0.1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("H");
        for (int i = 0; i < randomint; i++) {
            try {
                Thread.sleep(100); // Wait for 0.1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("m");
        }
        try {
            Thread.sleep(1000); // Wait for 0.1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public static void randomAngryReply(String talkerName, String raiserName, int randomReply) {
        String angryreply = null;
        switch (randomReply) {
            case 1:
                angryreply = "You mf";
                break;
            case 2:
                angryreply = "Arggh screw you " + raiserName;
                break;
            case 3:
                angryreply = "Basket sia " + raiserName;
                break;
            case 4:
                angryreply = "Ah crap";
                break;
            case 5:
                angryreply = "God DAMN IT";
                break;
            default:
                break;
        }

        System.out.print(talkerName + ": ");
        for (int i = 0; i < angryreply.length(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print((angryreply.charAt(i)));
        }

        System.out.println();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
