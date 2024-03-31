package Bot;

import java.util.ArrayList;
import java.util.Random;

import Players.Player;
import Players.PlayerBot;
import Base.*;
import Eval.*;

//utility class for bot
//Single Responsibility Principle

public class BasicBotActionCalculator {
    
<<<<<<< Updated upstream:CS102cardgame/src/Eval/BotActions.java
    // public int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1) {
    //     ArrayList<Card> hand = o.getHand();
    //     ArrayList<Card> commCards = table1.getCommCards();

    //     int handValue = HandEval.getHandValue(hand, commCards); //Evaluated using 2 hand cards and 5 community cards
    //     double chenScore = HandEval.calculateChenScore(hand); //Evaluated only using 2 hand cards

    //     PlayerBot pb = (PlayerBot) o;
    //     int botTightness = pb.getTightness(); //determines how aggresive the bot will be
    //     double chenScoreToPlay = botTightness / 5.0; //botTightness ranges from 0 - 100, hence chenScoreToPlay ranges from 0 - 20 (max and min score of chenscore formula)
        
=======
    public static int getBotAction(Player o, String previousAction, boolean afterBet1, Table table1) {
        ArrayList<Card> hand = o.getHand();
        ArrayList<Card> commCards = table1.getCommCards();

        int handValue = HandEval.getHandValue(hand, commCards);
        double chenScore = SimpleChenScoreCalculator.calculateChenScore(hand);

        PlayerBot pb = (PlayerBot) o;
        int botTightness = pb.getTightness();
        double chenScoreToPlay = botTightness / 5.0;
>>>>>>> Stashed changes:CS102cardgame/src/Bot/BasicBotActionCalculator.java

    //     if (previousAction == null) {
    //         previousAction = "Check";
    //     }

<<<<<<< Updated upstream:CS102cardgame/src/Eval/BotActions.java
    //     //the amount the bot will bet to is determined by tightness, handvalue and the remaining player balance.
    //     int increaseBetTo = (int)(300.0 * ((100.0 - botTightness)/100.0) * (handValue/425.0) + (0.15*o.getBalance()));
        
    //     int callAmt = table1.getCurrentBetAmt() - pb.getBet();
    //     if (afterBet1) {// occurs at the flop
    //         if ((handValue) <= 28 && (handValue > 7)) { // average hand
            
    //             if (o.getBet() < table1.getCurrentBetAmt()) {
    //                 /* Bot will fold if the called amount is a significant portion of remaining balance (determined
    //                 by tightness) and the bot did NOT all in */
    //                 if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
    //                     return 3;
    //                 }
    //                 return 1;
    //             }
    //             if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) { 
    //                 // risky bet by bot determined by tightness and randomness
    //                 Random random = new Random();
    //                 int ranInt = random.nextInt(10) + 1;
    //                 if (ranInt >= botTightness/10) {
    //                     return 2;
    //                 }
    //             }
                
    //             if (o.getBet() == table1.getCurrentBetAmt()) {
    //                 return 1;
    //             }
    //             return 1;
    //         } else if (handValue > 28) {// if handValue > 28 = if better than a pair of 2 good hand
                
    //             // if callAmt is significant portion of balance and bot has J pair or less, bot will fold
    //             if ((callAmt > 0.9 * o.getBalance() && handValue < 38)  && o.getBalance() != 0) {
    //                 return 3;
    //             }
    //             if (increaseBetTo <= table1.getCurrentBetAmt()) {
    //                 return 1; // Bot will check since its willing to bet less than the current bet amount of the table
    //             }
    //             if (increaseBetTo > table1.getCurrentBetAmt()) {
    //                 if (o.getBalance() == 0) { //bot has all - in, check so the round continues
    //                     return 1;
    //                 }
    //                 // prospective remaining balance < remaining balance limit hence check
    //                 if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) {
    //                     return 1;
    //                 }
    //                 return 2;
    //             }

    //         } else { // Bad hand
    //             //bot will take a free check if the previous action was a check or fold
    //             if (previousAction.equals("Check") || previousAction.equals("Fold")) { 
    //                 // System.out.println("i should check");
    //                 return 1;
    //             } else {
    //                 if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance()  && o.getBalance() != 0) {
    //                     return 3;
    //                 }
    //                 return 1;
    //             }
    //         }
    //         return 1;
    //     } else { //Bot utilizes the same logic pre flop and post flop, metrics of evluating the hand changes 
    //         if (chenScore < chenScoreToPlay) { // Bad Hand
    //             if (previousAction.equals("Check") || previousAction.equals("Fold")) {
    //                 return 1;
    //             } else {
                    
    //                 if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
    //                     return 3;
    //                 }
    //                 return 1;
    //             }
    //         } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0) { // Bot has a good initial had, might bet
                
    //             if ((callAmt > 0.9 * o.getBalance() && chenScore < 14) && o.getBalance() != 0) {
    //                 return 3;
    //             }
    //             if (increaseBetTo <= table1.getCurrentBetAmt()) {
    //                 return 1; 
    //             }
    //             if (increaseBetTo > table1.getCurrentBetAmt()) {
    //                 if (o.getBalance() == 0) {
    //                     return 1;
    //                 }
    //                 if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness/100.0 * o.getBalance()) {
    //                     return 1;
    //                 }
    //                 return 2;
    //             }
    //         } else { // Ok hand
                
    //             //checks if the amount the bot is willing to bet is less than the current bet amount
    //             if (o.getBet() < table1.getCurrentBetAmt()) {
    //                 if (callAmt > ((100.0 - botTightness)/100.0) * o.getBalance() && o.getBalance() != 0) {
    //                     return 3;
    //                 }
    //                 return 1;
    //             }
    //             if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) {
                    
    //                 Random random = new Random();
    //                 int ranInt = random.nextInt(10) + 1;
    //                 if (ranInt >= botTightness%10) {
    //                     // System.out.println("Risky bet");
    //                     return 2;
    //                 }
    //             }
                
    //             if (o.getBet() == table1.getCurrentBetAmt()) {
    //                 return 1;
    //             }
    //             return 1;
    //         } 
    //         return 1;
    //     }
    // } 

    public void botThinking(String name) { //prints out "hmmmm"
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
=======
        int increaseBetTo = (int) (300.0 * ((100.0 - botTightness) / 100.0) * (handValue / 425.0)
                + (0.15 * o.getBalance()));

        int callAmt = table1.getCurrentBetAmt() - pb.getBet();
        if (afterBet1) {// fold if raise too high for given hand value
            if ((handValue) <= 28 && (handValue > 7)) { // ok hand
                // consider if bot need to call
                if (o.getBet() < table1.getCurrentBetAmt()) {
                    if (callAmt > ((100.0 - botTightness) / 100.0) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) {
                    // risky bet by bot determined by tightness and randomness
                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness / 10) {
                        return 2;
                    }
                }

                if (o.getBet() == table1.getCurrentBetAmt()) {
                    return 1;
                }
                return 1;
            } else if (handValue > 28) {// if handValue > 28 = if better than a pair of 2 good hand

                if (callAmt > 0.9 * o.getBalance() && handValue < 38) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if Table.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness / 100.0 * o.getBalance()) { // prospective
                                                                                                                 // remaining
                                                                                                                 // balance
                                                                                                                 // <
                                                                                                                 // remaining
                                                                                                                 // balance
                                                                                                                 // limit
                        return 1;
                    }
                    return 2;
                }
            } else { // Bad hand
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    System.out.println("i should check");
                    return 1;
                } else {

                    if (callAmt > ((100.0 - botTightness) / 100.0) * o.getBalance()) {
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

                    if (callAmt > ((100.0 - botTightness) / 100.0) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
            } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0) { // Bot has good hadn enuf to bet

                if (callAmt > 0.9 * o.getBalance() && chenScore < 14) {
                    return 3;
                }
                if (increaseBetTo <= table1.getCurrentBetAmt()) {
                    return 1; // Call if Table.Bet = what I want to raise TO
                }
                if (increaseBetTo > table1.getCurrentBetAmt()) {
                    if (o.getBalance() == 0) {
                        return 1;
                    }
                    if (o.getBalance() - (increaseBetTo - o.getBet()) < botTightness / 100.0 * o.getBalance()) { // prospective
                                                                                                                 // remaining
                                                                                                                 // balance
                                                                                                                 // <
                                                                                                                 // remaining
                                                                                                                 // balance
                                                                                                                 // limit
                        return 1;
                    }
                    return 2;
                }
            } else { // Ok hand

                if (o.getBet() < table1.getCurrentBetAmt()) {
                    if (callAmt > ((100.0 - botTightness) / 100.0) * o.getBalance()) {
                        return 3;
                    }
                    return 1;
                }
                if (increaseBetTo > table1.getCurrentBetAmt() && o.getBalance() != 0) {

                    Random random = new Random();
                    int ranInt = random.nextInt(10) + 1;
                    if (ranInt >= botTightness % 10) {
                        System.out.println("Risky bet");
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

    public static int getBotRaiseAmt(int tightness, int handValue, Player o) { 
    // If betting, minimally a pair
    double bettingAmount = 300.0 * ((100.0 - tightness) / 100.0) * (handValue /425.0) + (0.15 * o.getBalance());
    return (int) bettingAmount;
>>>>>>> Stashed changes:CS102cardgame/src/Bot/BasicBotActionCalculator.java
    }
}
