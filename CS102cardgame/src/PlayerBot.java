// // package CS102cardgame.src;

import java.util.*;

public class PlayerBot extends Player {
    /** Tightness (0 = loose, 100 = tight). */
    // private int tightness;

    // /** Betting aggression (0 = safe, 100 = aggressive). */
    // private int aggression;

    public PlayerBot(String name, String type) {
        super(name, type);
        // this.tightness = tightness;
        // this.aggression = aggression;
    }

    public static int getBotAction(Player o, String previousAction, Boolean afterBet1, Table table1) {
        botThinking(o.getName());
        int handValue = Hand2.getHandValue(o.getHand(), table1.getCommCards());
        double chenScore = calculateChenScore(o.getHand());
        System.out.println(o.getName() + " : " + chenScore);

        // For a given handValue, where tightness = x, aggression = y
        // formula (handValue, tightness, aggression) - >returns action
        // Aggression -> likelihood to bet
        // Tightness -> AMOUNT to bet
        // betProb = (tightness * aggression) / 100
        // -> T1 = (handvalue / 425 * 100) * tightness^2
        // -> A1 = (handValue / 425 * 100) * aggression^2
        // Double randomTight = random.nextDouble(T1)
        // Double randomAggro = random.nextDouble(A1)
        // if randomTight > randomAggro -> betProb *= 0.5
        // if randomAggro > randomTight -> betProb *= 1.5
        // Double randDouble = random.nextDouble()
        // if (randDouble < betProb) bet
        // if (randDouble <0.8) check
        // else fold
        // Tightness 0-100, bigger number = less likely to bet
        // botBetAmt(handValue, aggression) -> returns raise amt based on handValue,
        // aggression, balance
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
            } else {// if handValue > 28 = if better than a pair of 2
                if (previousAction == null) {
                    previousAction = "Check";
                }
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    if (o.getBalance() == 0) {
                        System.out.println("I've all in-ed");
                        return 1;
                    }
                    return 2; // Raising if someone else check/fold AND better than pair of 2
                } else {
                    return 1;
                }
            }
        } else {
            if (chenScore < 3.0) {
                return 3;
            } else if (chenScore >= 3.0 && chenScore < 6.0) {
                if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    if (o.getBalance() == 0) {
                        System.out.println("I've all in-ed");
                    }
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
            // Chen score if not afterbet1

        }
    }

    public static int getBotRaiseAmt(Player o, Table table1) { // If betting, minimally a pair
        int tightness = 0; // 20-100
        int handValue = Hand2.getHandValue(o.getHand(), table1.getCommCards());
        Double percentage = ((double) handValue / 425) * 100 + (double) tightness;
        // int betAmt = (int)percentage * o.getBalance();

        return 0;
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
            Thread.sleep(500);
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
        System.out.println(score);
        return score;
    }

}

// public Action botAction (int currentBetAmt, int minBet, ArrayList<Card>
// playerHand,
// ArrayList<Action> availableActions){

// Action action = null;
// int bettingAmount = 0;
// double chenScore = calculateChenScore(playerHand);
// double chenScoreToPlay = tightness / 5.0;

// if (chenScore < chenScoreToPlay){ // bad hand
// if (!hasChecked){
// action = Action.Check;
// } else {
// action = Action.Fold;
// }
// } else if ((chenScore - chenScoreToPlay) >= (20 - chenScoreToPlay) / 4.0){
// //hand is good
// if (aggression == 0){
// if (availableActions.contains(Action.Call)){
// action = Action.Call;
// } else {
// action = Action.Check;
// }
// } else if (aggression == 100){
// bettingAmount = super.getChips();
// if (availableActions.contains(Action.Bet)){
// action = new BetAction(bettingAmount);
// } else if (availableActions.contains(Action.Raise)){
// action = new RaiseAction(bettingAmount);
// } else if (availableActions.contains(Action.Call)){
// action = Action.Call;
// } else {
// action = Action.Check;
// }

// } else {
// bettingAmount = minBet;
// int increments = aggression / 20;
// for (int i = 1; i < increments; i++){
// bettingAmount += minBet;
// }
// if (bettingAmount > super.getChips()){
// bettingAmount = super.getChips();
// }
// if (bettingAmount > currentBetAmt) {
// if (availableActions.contains(Action.Bet)) {
// action = new BetAction(bettingAmount);
// } else if (availableActions.contains(Action.Raise)){
// action = new RaiseAction(bettingAmount);
// } else if (availableActions.contains(Action.Call)){
// action = Action.Call;
// } else {
// action = Action.Check;
// }

// } else {
// if (availableActions.contains(Action.Call)){
// action = Action.Call;
// } else {
// action = Action.Check;
// }
// }
// }
// } else { //if ok hand
// if (availableActions.contains(Action.Call)){
// action = Action.Call;
// } else {
// action = Action.Check;
// }
// }
// return action;
// }
// }
