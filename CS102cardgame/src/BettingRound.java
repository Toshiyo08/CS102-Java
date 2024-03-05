import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Set;

import javax.swing.Action;

public class BettingRound {
    // private

    // checks each player with other player's check amount
    public Boolean isCheckedCalled(Player plyr, Table table1) {
        if (plyr.getBet() == table1.getCurrentBetAmt() && plyr.getCheckStatus() == true) {
            return true;
        } else {
            return false;
        }
    }

    // asks player for action and updates player's bet
    public Boolean isPlayerTurnComplete(Player plyr, int currentHighestBet, Table table1) {
        int numChecks = 0;
        Scanner plyrturn = new Scanner(System.in);
        Scanner plyrbet = new Scanner(System.in);
        int plyrBetInput = 0;
        String plyrturninput = null;
        Boolean isActionDone = false;
        while (!isActionDone) {
            System.out.println("What will you do?");

            if (currentHighestBet > plyr.getBet()) {
                System.out.println("Raise, Call or Fold");
                plyrturninput = plyrturn.nextLine();
                plyrturninput = plyrturn.nextLine();
                // DOES NOT WORK FOR BOTS, bots cannot input
                // Change plyrturninput to variable that takes in player/bot action


                //add exceptions for player input 
                //change player turn input to accept bot's input -> once bot receives prompt for action, it needs to create hand eval class instance and return 
                if (plyrturninput.equals("Call")) {
                    // If balance less than what they want to bet
                    if (plyr.getChips() < currentHighestBet) {
                        // All in
                        // Check for double counting
                        plyr.setCall(plyr.getChips());
                    } else {
                        plyr.setCall(currentHighestBet);
                        plyr.setCheckTrue();
                        isActionDone = true;
                    }

                } else if (plyrturninput.equals("Raise")) {
                    plyrBetInput = plyrbet.nextInt();
                    plyr.setRaise(plyrBetInput);
                    table1.raiseCurrentBetAmt(plyrBetInput);
                    plyrbet.close();
                    for (Player e : table1.getActivePlayers()) {
                        e.setCheckfalse();
                    }
                    isActionDone = true;
                } else if (plyrturninput.equals("Fold")) {
                    plyr.fold();
                    isActionDone = true;
                }
                plyrturn.close();

            } else {
                System.out.println("Raise, Call, Check or Fold");
                plyrturninput = plyrturn.nextLine();

                if (plyrturninput.equals("Check") && plyr.getBet() == table1.getCurrentBetAmt()) {
                    plyr.setCheckTrue();
                    isActionDone = true;
                } else if (plyrturninput.equals("Call") && currentHighestBet > plyr.getBet()) {
                    plyr.setCall(currentHighestBet);
                    plyr.setCheckTrue();
                    isActionDone = true;
                } else if (plyrturninput.equals("Raise")) {
                    plyrBetInput = plyrbet.nextInt();
                    plyr.setRaise(plyrBetInput);
                    table1.raiseCurrentBetAmt(plyrBetInput);
                    plyrbet.close();
                    isActionDone = true;
                } else if (plyrturninput.equals("Fold")) {
                    plyr.fold();
                    isActionDone = true;
                }
                plyrturn.close();
            }

        }

        return true;
    }

    //FOR BOT ACTIONS
    //chen formula for hole cards, only for the first 2 rounds
    //if entities bet is higher than current highest bet, can only check, raise or fold.
    //if balance is not enough and if bot choose to call or fold, need to all in no matter wht

    
    //check if == 2 cards in bot hands ?? 
    //if yes, use chen formula to decide bot's actions -> call the actions from player class
    //if no, use hand eval methods to decide bot's actions(for 5,6,7 cards)

    public void botAction(Player bot, Table table1) {
        //check bot hands -> use player class
        if (bot.getHand().size() <= 2){
            //chen formula -> use playerbot class?? , should put it under player class maybe
            //define tightness and aggression here?
            //PlayerBot bot1 = new PlayerBot("bot1", );
            double chenScore = bot.calculateChenScore(bot.getHand());
            double chenScoreToPlay = 0.2;
            if ((chenScore < chenScoreToPlay)) {
                if (table1.getCurrentBetAmt() > bot.getBet()) {
                    // Always check for free if possible.
                    bot.setCheckTrue();
                } else {
                    // Bad hole cards; play tight.
                    bot.fold();
                }
            } else {
                // Good enough hole cards, play hand.
                if ((chenScore - chenScoreToPlay) >= ((20.0 - chenScoreToPlay) / 2.0)) {
                    // Very good hole cards; bet or raise!
                    if (table1.getCurrentBetAmt() > bot.getBet()) {
                        bot.setCall(table1.getCurrentBetAmt());
                        bot.setCheckTrue();
                    } else {
                        bot.setRaise(20);
                        table1.raiseCurrentBetAmt(20);
                    }
                } else {
                    // Decent hole cards; check or call.
                    if (table1.getCurrentBetAmt() > bot.getBet()) {
                        bot.setCall(table1.getCurrentBetAmt());
                        bot.setCheckTrue();
                    } else {
                        bot.setCheckTrue();
                    }
                }
            }
        } else {
            //hand eval methods
            //if entities bet is higher than current highest bet, can only check, raise or fold.
            //if balance is not enough and if bot choose to call or fold, need to all in no matter wht
            //assess the 3rd card-> use hand eval to assess bot card, if 3rd card hand eval value is higher than the other 2?
            Hand hand = new Hand();
            int handValue = hand.getHandValue(bot.getHand(), table1.getCommCards());
            
        }
    }





    // public Action act(BigDecimal minBet, BigDecimal currentBet, Set<Action> allowedActions) {
    //     Player action;
    //     if (allowedActions.size() == 1) {
    //         // No choice, must check.
    //         action = Action.CHECK;
    //     } else {
    //         double chenScore = PokerUtils.getChenScore(cards);
    //         double chenScoreToPlay = tightness * 0.2;
    //         if ((chenScore < chenScoreToPlay)) {
    //             if (allowedActions.contains(Action.CHECK)) {
    //                 // Always check for free if possible.
    //                 action = Action.CHECK;
    //             } else {
    //                 // Bad hole cards; play tight.
    //                 action = Action.FOLD;
    //             }
    //         } else {
    //             // Good enough hole cards, play hand.
    //             if ((chenScore - chenScoreToPlay) >= ((20.0 - chenScoreToPlay) / 2.0)) {
    //                 // Very good hole cards; bet or raise!
    //                 if (aggression == 0) {
    //                     // Never bet.
    //                     if (allowedActions.contains(Action.CALL)) {
    //                         action = Action.CALL;
    //                     } else {
    //                         action = Action.CHECK;
    //                     }
    //                 } else if (aggression == 100) {
    //                     // Always go all-in!
    //                     //FIXME: Check and bet/raise player's remaining cash.
    //                     BigDecimal amount = (tableType == TableType.FIXED_LIMIT) ? minBet : minBet.multiply(BigDecimal.TEN.multiply(BigDecimal.TEN));
    //                     if (allowedActions.contains(Action.BET)) {
    //                         action = new BetAction(amount);
    //                     } else if (allowedActions.contains(Action.RAISE)) {
    //                         action = new RaiseAction(amount);
    //                     } else if (allowedActions.contains(Action.CALL)) {
    //                         action = Action.CALL;
    //                     } else {
    //                         action = Action.CHECK;
    //                     }
    //                 } else {
    //                     BigDecimal amount = minBet;
    //                     if (tableType == TableType.NO_LIMIT) {
    //                         int betLevel = aggression / 20;
    //                         for (int i = 0; i < betLevel; i++) {
    //                             amount = amount.add(amount);
    //                         }
    //                     }
    //                     if (currentBet.compareTo(amount) < 0) {
    //                         if (allowedActions.contains(Action.BET)) {
    //                             action = new BetAction(amount);
    //                         } else if (allowedActions.contains(Action.RAISE)) {
    //                             action = new RaiseAction(amount);
    //                         } else if (allowedActions.contains(Action.CALL)) {
    //                             action = Action.CALL;
    //                         } else {
    //                             action = Action.CHECK;
    //                         }
    //                     } else {
    //                         if (allowedActions.contains(Action.CALL)) {
    //                             action = Action.CALL;
    //                         } else {
    //                             action = Action.CHECK;
    //                         }
    //                     }
    //                 }
    //             } else {
    //                 // Decent hole cards; check or call.
    //                 if (allowedActions.contains(Action.CHECK)) {
    //                     action = Action.CHECK;
    //                 } else {
    //                     action = Action.CALL;
    //                 }
    //             }
    //         }
    //     }
    //     return action;
    // }
    
    

}

// Boolean answered. Only if answered can it move on.
// Answered only takes in 4 strings: Fold, Raise, Check, Call

// While asking for betting amt or any int,
// if letters are entered instead of numbers, try catch ICE 7 Q3:
// InputMismatchException