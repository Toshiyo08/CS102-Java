package GameRound;

import java.util.*;

import Base.*;
import Eval.*;
import Input.*;
import Players.*;

public class Game {

    /** The maximum number of community cards. */
    private InputHandler inputHandler;
    //private PlayerBot PlayerBot;

    public Game(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    public void bettingRound(ArrayList<Player> playersList, Table table1, boolean afterRound1,
        Player[] turnOrder, int numTimesBet) {
        ArrayList<Player> currentPlayers = new ArrayList<Player>();
        // active users
        for (Player o : turnOrder) {
            if (o.getIsPlaying() == true) {
                //check active players
                currentPlayers.add(o);
            }
        }
        
        int numPlayersChecked = 0;
        String previousAction = null;
        String bettingRoundName = null;
        switch (numTimesBet) {
            case 1:
                bettingRoundName = "Pre-Flop";
                break;
            case 2:
                bettingRoundName = "Flop";
                break;
            case 3:
                bettingRoundName = "Turn";
                break;
            case 4:
                bettingRoundName = "River";
            default:
                bettingRoundName = "Showdown";
                break;
        }

        while (numPlayersChecked != getCurrentSize(currentPlayers)) {
            for (Player o : currentPlayers) {
                if (o.getType().equals("Player") && o.getIsChecked() == false && o.getIsPlaying() == true) {
                    System.out.println("Betting Phase: " + bettingRoundName);
                    String action = null;
                    GameTextDisplay.showPlayerAttributes(o, table1, afterRound1);
                    if (action == null){
                        action = "check";
                    }

                    // If Big Blind
                    if (!afterRound1 && o.getIsBigBlind() && !o.getIsBlindPaid()) {
                        System.out.println("Pot: Big Blind! That's $10 thanks");

                        //modified this, want to modify for small blind
                        action = inputHandler.getBigBlindInput(action, o);

                        // If Small Blind
                    } else if (!afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) {
                        System.out.println("Pot: Small Blind! That's $5 thanks");
                        action = GameTextDisplay.getSmallBlindInput(o, table1, inputHandler, action);
                        
                        // Non-blind betting round
                    } else {
                        action = GameTextDisplay.getNonBlindPlayerInput(o, table1, inputHandler, action);
                    }

                    if (action.equals("check")) { // Can only check if bet matches table bet
                        o.setIsChecked(true);
                        numPlayersChecked++;
                        previousAction = "Check";
                    }
                    if (action.equals("call")) {
                        int raiseAmt = table1.getCurrentBetAmt() - o.getBet();
                        if (raiseAmt > o.getBalance()) {
                            raiseAmt = o.getBalance();
                        }
                        o.raiseBet(raiseAmt);
                        table1.raisePot(raiseAmt);

                        if (o.getBalance() == 0) {
                            System.out.println("I'm all in baby");
                        }
                        System.out.println("Your new balance: " + o.getBalance());
                        System.out.println("Your new Bet: " + o.getBet());
                        System.out.println("Table's new pot: " + table1.getPot());
                        o.setIsChecked(true);
                        numPlayersChecked++;
                        previousAction = "Check";
                    }
                    if (action.equals("bet") || action.equals("raise")) {
                        int newBet = inputHandler.getBetInput(o, action); // Amt X player increases by

                        while (newBet <= table1.getCurrentBetAmt()) {
                            if (newBet >= o.getBalance()) {
                                System.out.println("All in!");
                                newBet = o.getBalance();
                                break;
                            }
                            System.out.println("Not enough! You need to raise to more than $"
                                    + (table1.getCurrentBetAmt()));
                            newBet = inputHandler.getBetInput(o, action);
                        }
                        if (newBet > table1.getCurrentBetAmt()) {
                            table1.setCurrentBetAmt(newBet);
                        }

                        if (o.getBet() != 0) {
                            newBet = newBet - o.getBet();
                        }
                        o.raiseBet(newBet); // Bet increased by X and Balance decreased by X
                        table1.raisePot(newBet); // Pot increased by X

                        o.setIsChecked(true);
                        numPlayersChecked = 1;
                        for (Player e : currentPlayers) {
                            if (!e.equals(o)) {
                                e.setIsChecked(false);
                            }
                        }
                        System.out.println("Your new balance: " + o.getBalance());
                        System.out.println("Your new Bet " + o.getBet());
                        System.out.println("Table's new pot " + table1.getPot());
                        previousAction = "Bet";
                    }

                    if (action.equals("fold")) {
                        o.setIsPlaying(false);
                        previousAction = "Fold";
                    }
                    System.out.println("-------------------------------------------------------------------------------");
                } else if (o.getType().equals("Bot") && o.getIsChecked() == false && o.getIsPlaying() == true) {

                    GameTextDisplay.showPlayerAttributes(o, table1, afterRound1);

                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) { // Call
                            int callAmt = table1.getCurrentBetAmt() - o.getBet();
                            if (o.getBalance() == 0) { // Already broke
                                System.out.println("Check, I've all in-ed");
                                wait(2000);
                            } else { // Not broke yet
                                if (callAmt > o.getBalance()) {
                                    callAmt = o.getBalance();
                                }
                                o.raiseBet(callAmt);
                                table1.raisePot(callAmt);
                            }

                            if (o.getBalance() == 0) {
                                System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                                        + "thing left and my bet is $" + o.getBet());
                                wait(2000);
                            } else {
                                PlayerBot.botThinking(o.getName());
                                System.out.println(o.getName() + ": $" + table1.getCurrentBetAmt() + "? I call");
                                wait(2000);
                            }

                            o.setIsChecked(true);
                            numPlayersChecked++;
                            previousAction = "Check";
                        } else { // Check
                            System.out.println(o.getName() + ": I Check");

                            o.setIsChecked(true);
                            numPlayersChecked++;
                            previousAction = "Check";
                        }
                        wait(2000);
                    } else if (botAction == 2) {
                        int handValue = HandEval.getHandValue(o.getHand(), table1.getCommCards());
                        PlayerBot pb = (PlayerBot) o;
                         // How much bot wants to
                        int bettingAmount = (int)(300.0 * ((100.0 - pb.getTightness())/100.0) * (handValue/425.0) + (0.15*o.getBalance()));
                        

                        // If the amount to bet is almost the balance, might as well all in
                        if (bettingAmount >= 0.75 * o.getBalance()) { 
                            bettingAmount = o.getBalance();
                        }

                        if (bettingAmount >= o.getBalance()) {// Not enough to bet/raise what YOU want-> All in
                            System.out.println(o.getName() + ": Oh, I don't have enough, guess I'll all in");
                            bettingAmount = o.getBalance();
                            if (o.getBet() + bettingAmount > table1.getCurrentBetAmt()) { // All in increases table bet
                                table1.raiseCurrentBetAmt((o.getBet() + bettingAmount) - table1.getCurrentBetAmt());
                            } // else, don't need change table bet
                            o.raiseBet(bettingAmount);
                            table1.raisePot(bettingAmount);
                            wait(2000);

                        } else { // Enough to bet/raise what YOU want
                            table1.setCurrentBetAmt(bettingAmount);
                            if (o.getBet() != 0) { // If someone bet, raising
                                bettingAmount -= o.getBet();
                            }
                            o.raiseBet(bettingAmount);
                            table1.raisePot(bettingAmount);
                        }
                        if (o.getBalance() == 0) {
                            System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                                    + "thing left and my bet is $" + o.getBet());
                            wait(2000);
                        } else {
                            if (!afterRound1) {
                                if (table1.getCurrentBetAmt() == 0) {
                                    System.out.println(o.getName() + ": I bet to $" + o.getBet());
                                } else {
                                    System.out.println(o.getName() + ": I raise to $" + o.getBet());
                                }
                            } else {
                                if (table1.getCurrentBetAmt() == 10) {
                                    System.out.println(o.getName() + ": I bet to $" + o.getBet());
                                } else {
                                    System.out.println(o.getName() + ": I raise to $" + o.getBet());
                                }
                            }
                            wait(2000);
                        }
                        //set raiser to true and set everyone else to false
                        for (Player e : currentPlayers) {
                            e.setIsChecked(false);
                        }
                        o.setIsChecked(true);
                        numPlayersChecked = 1;
                        previousAction = "Bet";
                    } else if (botAction == 3) {
                        System.out.println(o.getName() + ": I'm folding");
                        wait(2000);
                        o.setIsPlaying(false);
                        previousAction = "Fold";
                    }

                    if (!afterRound1 && (o.getIsBigBlind() || o.getIsSmallBlind()) && !o.getIsBlindPaid()) {
                        o.setIsBlindPaid(true);
                    }
                    System.out.println("-------------------------------------------------------------------------------");
                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (getCurrentSize(currentPlayers) == 1) {
                    return;
                }
                
            }

        }

        for (Player o : playersList) {
            o.setIsChecked(false);
            o.setBet(0);
        }
        table1.setCurrentBetAmt(0);
        System.out.println("This concludes the betting for " + bettingRoundName + " phase");
        wait(2000);
        System.out.println();
    }

   
    public static int getCurrentSize(ArrayList<Player> currentPlayers) {
        int numCurrentPlayers = 0;
        for (Player o : currentPlayers) {
            if (o.getIsPlaying()) {
                numCurrentPlayers++;
            }
        }
        return numCurrentPlayers;
    }

    public static void wait(int waitTime) {
        try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static boolean timeToOpenHand(ArrayList<Player> playersList, Table table1, Deck deck1, int numTimesBet) {
        boolean isOpenHandTime = false;
        int bankruptCounter = 0;
        int activePlayerCounter = 0;
        ArrayList<Player> activePlayers = new ArrayList<Player>();
        for (Player g : playersList) {
            if (g.getIsPlaying()) {
                activePlayerCounter++;
                if (g.getBalance() == 0) {
                    bankruptCounter++;
                }
                activePlayers.add(g);
            }
        }
        if (bankruptCounter >= activePlayerCounter - 1 || activePlayerCounter == 1) {
            isOpenHandTime = true;
            System.out.println("OPENHAND");
        } else {
            return false;
        }

        if (isOpenHandTime) {
            System.out.println("Everybody open hand!");
            if (numTimesBet == 1) { // Pre-Flop
                for (int i = 0; i < 5; i++) {
                    deck1.burnCard();
                    table1.drawComm(deck1.dealCard());
                }
            } else {// Post-Flop, 3 cards already there
                for (int i = 0; i < 4 - numTimesBet; i++) {
                    deck1.burnCard();
                    table1.drawComm(deck1.dealCard());
                }
            }

            //if everybody all in or all but one all in, then show hand
            GameTextDisplay.printCard(table1.getCommCards());
            GameTextDisplay.printShowDown(activePlayers);
            // DELETE ONLY FOR MAC DEBUGGING BELOW
            for (Card card : table1.getCommCards()){
                System.out.println(card.getRank() + " of " + card.getSuit());
            }
            for (Player plyr : activePlayers){
                for (Card card : plyr.getHand()){
                    System.out.println(card.getRank() + " of " + card.getSuit());
                }
                
            }
            // DELETE ABOVE ONLY FOR MAC DEBUGGING ABOVE
            Winner.getWinner(playersList, table1);

            System.out.println("Round over");

            return true;
        }
        return false;
    }

    public static void resetRound(ArrayList<Player> playersList, Table table1) {
        for (Player o : playersList) {
            o.setIsChecked(false);
            o.getHand().clear();
            if (o.getBalance() != 0) {
                o.setIsPlaying(true);
            }
            o.setBet(0);
        }
        table1.getCommCards().clear();
        table1.setCurrentBetAmt(0);
        table1.setPot(0);
    }
}