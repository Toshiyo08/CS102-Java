package Players;
// // package CS102cardgame.src;

import java.util.*;

import Base.*;
import Eval.*;
import GameRound.Game;

//Bot class is an extension of player!
//should not be a class in itself.
//Open-closed principle

public class PlayerBot extends Player{
    private int tightness;

    public PlayerBot(String name, String type, int tightness) {
        super(name, type);
        this.tightness = tightness;
    }

    public int getTightness(){
        return tightness;
    }

    public static int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1) {
        ArrayList<Card> hand = o.getHand();
        ArrayList<Card> commCards = table1.getCommCards();

        int handValue = HandEval.getHandValue(hand, commCards); //Evaluated using 2 hand cards and 5 community cards
        double chenScore = HandEval.calculateChenScore(hand); //Evaluated only using 2 hand cards

        PlayerBot pb = (PlayerBot) o;
        int botTightness = pb.getTightness(); //determines how aggresive the bot will be
        double chenScoreToPlay = botTightness / 5.0; //botTightness ranges from 0 - 100, hence chenScoreToPlay ranges from 0 - 20 (max and min score of chenscore formula)
        

        if (previousAction == null) {
            previousAction = "Check";
        }

        //the amount the bot will bet to is determined by tightness, handvalue and the remaining player balance.
        int increaseBetTo = (int)(300.0 * ((100.0 - botTightness)/100.0) * (handValue/425.0) + (0.15*o.getBalance()));
        
        int callAmt = table1.getCurrentBetAmt() - pb.getBet();
        if (afterBet1) {// occurs at the flop
            if ((handValue) <= 28 && (handValue > 7)) { // average hand
            
                if (o.getBet() < table1.getCurrentBetAmt()) {
                    /* Bot will fold if the called amount is a significant portion of remaining balance (determined
                    by tightness) and the bot did NOT all in */
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) { 
                    // risky bet by bot determined by tightness and randomness
                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness/10) {
                        return 2;
                    }
                }
                
                if (o.getBet() == table1.getCurrentBetAmt()) {
                    return 1;
                }
                return 1;
            } else if (handValue > 28) {// if handValue > 28 = if better than a pair of 2 good hand
                
                // if callAmt is significant portion of balance and bot has J pair or less, bot will fold
                if ((callAmt > 0.9 * o.getBalance() && handValue < 38)  && o.getBalance() != 0) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Bot will check since its willing to bet less than the current bet amount of the table
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) { //bot has all - in, check so the round continues
                        return 1;
                    }
                    // prospective remaining balance < remaining balance limit hence check
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) {
                        return 1;
                    }
                    return 2;
                }

            } else { // Bad hand
                //bot will take a free check if the previous action was a check or fold
                if (previousAction.equals("Check") || previousAction.equals("Fold")) { 
                    // System.out.println("i should check");
                    return 1;
                } else {
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance()  && o.getBalance() != 0) {
                        return 3;
                    }
                    return 1;
                }
            }
            return 1;
        } else { //Bot utilizes the same logic pre flop and post flop, metrics of evluating the hand changes 
            if (chenScore < chenScoreToPlay) { // Bad Hand
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    return 1;
                } else {
                    
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
                        return 3;
                    }
                    return 1;
                }
            } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0) { // Bot has a good initial had, might bet
                
                if ((callAmt > 0.9 * o.getBalance() && chenScore < 14) && o.getBalance() != 0) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; 
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) {
                        return 1;
                    }
                    return 2;
                }
            } else { // Ok hand
                
                //checks if the amount the bot is willing to bet is less than the current bet amount
                if (o.getBet() < table1.getCurrentBetAmt()) {
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) {
                    
                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness%10) {
                        // System.out.println("Risky bet");
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
        Game.wait(100);
        System.out.print("H");
        for (int i = 0; i < randomint; i++) {
            Game.wait(100);
            System.out.print("m");
        }
        try {
            Thread.sleep(1000); // Wait for 0.1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
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
