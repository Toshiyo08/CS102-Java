// // package CS102cardgame.src;

import java.util.*;

import javax.swing.Action;

import javax.swing.Action;

public class PlayerBot extends Player {
    /** Tightness (0 = loose, 100 = tight). */
    // private int tightness;

    // /** Betting aggression (0 = safe, 100 = aggressive). */
    // private int aggression;

    // /** Evaluate strength of starting hand */
    // private double chenscore;

    public PlayerBot(String name, String type) {
        super(name, type);
        // this.tightness = tightness;
        // this.aggression = aggression;
    }

    public static int getBotAction (Player o, String previousAction, Boolean afterBet1, Table table1) {
        botThinking(o.getName());
        int tightness = 0;
        int aggression = 0;
        int handValue = Hand2.getHandValue(o.getHand(), table1.getCommCards());

        if (afterBet1) {// fold if raise too high for given hand value
            if ((handValue) <= 28 && (handValue > 0)) {
                if (previousAction == null) {
                    previousAction = "Check";
                }
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    return 1;
                } else {
                    return 3;
                }
            } else {
                if (previousAction == null) {
                    previousAction = "Check";
                }
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    if (o.getBalance() == 0) {
                        System.out.println("I've all in-ed");
                        return 1;
                    }
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        // Chen score if not afterbet1
        return 1;
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
        System.out.println();
    }

    public static void randomangryreply(String talkerName, String raiserName, int randomreply) {
        String angryreply = null;
        switch (randomreply) {
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
        for (int i = 0 ; i < angryreply.length(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print((angryreply.charAt(i)));
        }

        System.out.println();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

//     public double calculateChenScore(List<Card> hand) {
//         double chenscore = 0;

//         Card card1 = hand.get(0);
//         Card card2 = hand.get(1);
//         int rank1 = card1.getRank();
//         String temp_suit1 = card1.getSuit();
//         int rank2 = card2.getRank();
//         String temp_suit2 = card2.getSuit();
//         int suit1;
//         int suit2;
//         if (temp_suit1.equals("Ace")) {
//             suit1 = 14;
//         } else if (temp_suit1.equals("King")) {
//             suit1 = 13;
//         } else if (temp_suit1.equals("Queen")) {
//             suit1 = 12;
//         } else if (temp_suit1.equals("Jack")) {
//             suit1 = 11;
//         } else {
//             suit1 = Integer.parseInt(temp_suit1);
//         }
//         if (temp_suit2.equals("Ace")) {
//             suit2 = 14;
//         } else if (temp_suit2.equals("King")) {
//             suit2 = 13;
//         } else if (temp_suit2.equals("Queen")) {
//             suit2 = 12;
//         } else if (temp_suit2.equals("Jack")) {
//             suit2 = 11;
//         } else {
//             suit2 = Integer.parseInt(temp_suit2);
//         }
//         int highRank = Math.max(rank1, rank2);
//         int lowRank = Math.min(rank1, rank2);
//         int rankDiff = highRank - lowRank;
//         int gap = (rankDiff > 1) ? rankDiff - 1 : 0;
//         boolean isPair = (rank1 == rank2);
//         boolean isSuited = (suit1 == suit2);

//         double score;

//         // 1. Base score highest rank only
//         // Ace
//         if (highRank == 14) {
//             score = 10.0;
//         } else if (highRank == 13) {
//             score = 8.0;
//         } else if (highRank == 12) {
//             score = 7.0;
//         } else if (highRank == 11) {
//             score = 6.0;
//         } else {
//             score = (highRank + 2) / 2.0;
//         }

//         // 2. If pair, double score, with minimum score of 5.
//         if (isPair) {
//             score *= 2.0;
//             if (score < 5.0) {
//                 score = 5.0;
//             }
//         }

//         // 3. If suited, add 2 points.
//         if (isSuited) {
//             score += 2.0;
//         }

//         // 4. Subtract points for gap.
//         if (gap == 1) {
//             score -= 1.0;
//         } else if (gap == 2) {
//             score -= 2.0;
//         } else if (gap == 3) {
//             score -= 4.0;
//         } else if (gap > 3) {
//             score -= 5.0;
//         }

//         // 5. Add 1 point for a 0 or 1 gap and both cards lower than a Queen.
//         if (!isPair && gap < 2 && rank1 < 12 && rank2 < 12) {
//             score += 1.0;
//         }

//         // Minimum score is 0.
//         if (score < 0.0) {
//             score = 0.0;
//         }

//         // 6. Round half point scores up.
//         return (double) Math.round(score);
//     }

//     public Action botAction (int currentBetAmt, int minBet, ArrayList<Card> playerHand, 
//     ArrayList<Action> availableActions){

//         Action action = null;
//         int bettingAmount = 0;
//         double chenScore = calculateChenScore(playerHand);
//         double chenScoreToPlay = tightness / 5.0;

//         if (chenScore < chenScoreToPlay){ // bad hand
//             if (!hasChecked){
//                 action = Action.Check;
//             } else {
//                 action = Action.Fold;
//             }
//         } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0){ //hand is good 
//             if (aggression == 0){
//                 if (availableActions.contains(Action.Call)){
//                     action = Action.Call;
//                 } else {
//                     action = Action.Check;
//                 }
//             } else if (aggression == 100){
//                 bettingAmount = super.getChips();
//                 if (availableActions.contains(Action.Bet)){
//                     action = new BetAction(bettingAmount);
//                 } else if (availableActions.contains(Action.Raise)){
//                     action = new RaiseAction(bettingAmount);
//                 } else if (availableActions.contains(Action.Call)){
//                     action = Action.Call;
//                 } else {
//                     action = Action.Check;
//                 }
                
//             } else {
//                 bettingAmount = minBet;
//                 int increments = aggression / 20;
//                 for (int i = 1; i < increments; i++){
//                     bettingAmount += minBet;
//                 }
//                 if (bettingAmount > super.getChips()){
//                     bettingAmount = super.getChips();
//                 }
//                 if (bettingAmount > currentBetAmt) {
//                     if (availableActions.contains(Action.Bet)) {
//                         action = new BetAction(bettingAmount);
//                     } else if (availableActions.contains(Action.Raise)){
//                         action = new RaiseAction(bettingAmount);
//                     } else if (availableActions.contains(Action.Call)){
//                         action = Action.Call;
//                     } else {
//                         action = Action.Check;
//                     }
                
//                 } else {
//                     if (availableActions.contains(Action.Call)){
//                         action = Action.Call;
//                     } else {
//                         action = Action.Check;
//                     }
//                 }
//             }
//         } else { //if ok hand 
//             if (availableActions.contains(Action.Call)){
//                 action = Action.Call;
//             } else {
//                 action = Action.Check;
//             }
//         }
//         return action;
//     }
// }
