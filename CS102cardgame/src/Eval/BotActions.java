package Eval;

import java.util.*;

import Base.*;
import Players.*;
import GameRound.*;


public class BotActions implements BotLogic{
    
    public int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1) {
        ArrayList<Card> hand = o.getHand();
        ArrayList<Card> commCards = table1.getCommCards();

        int handValue = HandEval.getHandValue(hand, commCards);
        double chenScore = HandEval.calculateChenScore(hand);

        PlayerBot pb = (PlayerBot) o;
        int botTightness = pb.getTightness();
        double chenScoreToPlay = botTightness / 5.0;
        

        if (previousAction == null) {
            previousAction = "Check";
        }

        int increaseBetTo = (int)(300.0 * ((100.0 - botTightness)/100.0) * (handValue/425.0) + (0.15*o.getBalance()));
        
        int callAmt = table1.getCurrentBetAmt() - pb.getBet();
        if (afterBet1) {// fold if raise too high for given hand value
            if ((handValue) <= 28 && (handValue > 7)) { // ok hand
                //consider if bot need to call
                if (o.getBet() < table1.getCurrentBetAmt()) {
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
                
                if ((callAmt > 0.9 * o.getBalance() && handValue < 38)  && o.getBalance() != 0) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if Table.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) { // prospective remaining balance < remaining balance limit
                        return 1;
                    }
                    return 2;
                }
            } else { // Bad hand
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    return 1;
                } else {
                    
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance()  && o.getBalance() != 0) {
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
                    
                    if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
                        return 3;
                    }
                    return 1;
                }
            } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0) { // Bot has good hadn enuf to bet
                
                if ((callAmt > 0.9 * o.getBalance() && chenScore < 14) && o.getBalance() != 0) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if Table.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) { // prospective remaining balance < remaining balance limit
                        return 1;
                    }
                    return 2;
                }
            } else { // Ok hand
                
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

    public void botThinking(String name) {
        System.out.print(name + ": ");
        Game.wait(500);
        Random random = new Random();
        int randomint = random.nextInt(6) + 1;
        Game.wait(100);
        System.out.print("H");
        for (int i = 0; i < randomint; i++) {
            Game.wait(100);
            System.out.print("m");
        }
        Game.wait(1000);
        System.out.println();
    }
}
