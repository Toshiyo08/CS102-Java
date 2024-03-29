package GameRound;

//package CS102cardgame.src;

import java.util.*;

import Base.*;
import Eval.*;
import Input.*;
import Players.*;

public class Game {

    /** The maximum number of community cards. */
    private InputHandler inputHandler;

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
                    

                    // If Big Blind
                    if (!afterRound1 && o.getIsBigBlind() && !o.getIsBlindPaid()) {
                        System.out.println("Pot: Big Blind! That's $10 thanks");

                        //modified this, want to modify for small blind
                        action = inputHandler.getBigBlindInput(action, o);

                        // If Small Blind
                    } else if (!afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) {
                        System.out.println("Pot: Small Blind! That's $5 thanks");
                        while (true) {
                            try {
                                if (o.getBet() < table1.getCurrentBetAmt()) { // Someone raised
                                    if (table1.getCurrentBetAmt() == 10) {
                                        System.out.println("Call / Bet / Fold");
                                        System.out.print("Action> ");
                                        action = inputHandler.getInput();
                                        if (action.equals("check") || action.equals("raise")) {
                                            throw new InputMismatchException("You cannot " + action + "!");
                                        }
                                    } else {
                                        System.out.println("Call / Raise / Fold");
                                        System.out.print("Action> ");
                                        action = inputHandler.getInput();
                                        if (action.equals("check") || action.equals("bet")) {
                                            throw new InputMismatchException("You cannot " + action + "!");
                                        }
                                    }

                                } else { // No one raised yet
                                    System.out.println("Check / Bet / Fold");
                                    System.out.print("Action> ");
                                    action = inputHandler.getInput();
                                    if (action.equals("call") || action.equals("raise")) {
                                        throw new InputMismatchException("You cannot " + action + "!");
                                    }
                                }
                                o.setIsBlindPaid(true);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        // Normal betting round
                    } else {

                        // If broke
                        if (o.getBalance() == 0) {
                            while (true) {
                                try {
                                    System.out.println("Check / ??   / Fold");
                                    System.out.print("Action> ");
                                    action = inputHandler.getInput();
                                    if (action.equals("call") || action.equals("raise") || action.equals("bet")) {
                                        throw new InputMismatchException("You cannot " + action + "!");
                                    }
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println(e.getMessage());

                                }
                            }

                            // If not broke
                        } else {
                            while (true) {
                                try {
                                    // Someone raised
                                    if (o.getBet() < table1.getCurrentBetAmt()) {

                                        // Not enough to raise beyond
                                        if (o.getBalance() + o.getBet() <= table1.getCurrentBetAmt()) {
                                            System.out.println("Call (All in) /       / Fold");
                                            System.out.print("Action> ");
                                            action = inputHandler.getInput();

                                            if (action.equals("check") || action.equals("raise")
                                                    || action.equals("bet")) {
                                                throw new InputMismatchException("You cannot " + action + "!");
                                            }
                                            break;

                                            // Enough to call OR raise beyond
                                        } else {
                                            System.out.println("Call / Raise / Fold");
                                            System.out.print("Action> ");
                                            action = inputHandler.getInput();

                                            if (action.equals("check") || action.equals("bet")) {
                                                throw new InputMismatchException("You cannot " + action + "!");
                                            }
                                            break;
                                        }

                                        // No one raised
                                    } else {
                                        System.out.println("Check / Bet / Fold");
                                        System.out.print("Action> ");
                                        action = inputHandler.getInput();

                                        if (action.equals("call") || action.equals("raise")) {
                                            throw new InputMismatchException("You cannot " + action + "!");
                                        }
                                        break;
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println(e.getMessage());
                                }
                            }

                        }
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
                        System.out.println("Your new Bet " + o.getBet());
                        System.out.println("Table's new pot " + table1.getPot());
                        o.setIsChecked(true);
                        numPlayersChecked++;
                        previousAction = "Check";
                    }
                    if (action.equals("bet") || action.equals("raise")) {
                        if (action.equals("bet")) {
                            System.out.print("Bet to?> ");
                        } else {
                            System.out.print("Raise to?> ");
                        }
                        int newBet = inputHandler.getBetInput(o); // Amt X player increases by

                        while (newBet <= table1.getCurrentBetAmt()) {
                            if (newBet >= o.getBalance()) {
                                System.out.println("All in!");
                                newBet = o.getBalance();
                                break;
                            }
                            System.out.println("Not enough! You need to raise to more than $"
                                    + (table1.getCurrentBetAmt()));
                            newBet = inputHandler.getBetInput(o);
                        }
                        if (newBet > table1.getCurrentBetAmt()) {
                            // table1.raiseCurrentBetAmt(newBet - table1.getCurrentBetAmt()); // TableBet
                            // increased by X
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

                } else if (o.getType().equals("Bot") && o.getIsChecked() == false && o.getIsPlaying() == true) {
                    // table1.getCurrentBetAmt());
                    // Card.printCard(o.getHand());

                    // THIS IS ONLY HERE FOR DEBUGGING DO NOT DELETE OR CLEAN
                    // Once game is live, remove showPlayerAttributes for bots
                    // AND add small and big blind changer for bot
                    GameTextDisplay.showPlayerAttributes(o, table1, afterRound1);
                    // UNCOMMENT THESE WHEN LIVE DO NOT DELETE OR CLEAN -> hopefully makes bots pay
                    // the big/small blind
                    // if (!afterRound1 && o.getBigBlind() && !o.getisBlindPaid()) {
                    // o.raiseBet(10);
                    // table1.raiseCurrentBetAmt(10);
                    // table1.raisePot(10);
                    // } else if (!afterRound1 && o.getSmallBlind() && !o.getisBlindPaid()) {
                    // o.raiseBet(5);
                    // table1.raisePot(5);
                    // }

                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) { // Call
                            int callAmt = table1.getCurrentBetAmt() - o.getBet();
                            if (o.getBalance() == 0) { // Already broke
                                System.out.println("Check, I've all in-ed");
                            } else { // Not broke yet
                                if (callAmt > o.getBalance()) {
                                    callAmt = o.getBalance();
                                }
                                o.raiseBet(callAmt);
                                table1.raisePot(callAmt);
                            }
                            
                            // if (callAmt > o.getBalance() && o.getBalance() != 0) {
                            //     callAmt = o.getBalance();
                            // }
                            // o.raiseBet(callAmt);
                            // table1.raisePot(callAmt);

                            if (callAmt == 5 && !afterRound1 && o.getIsSmallBlind() && !o.getIsBlindPaid()) {
                                System.out.println(o.getName() + ": Small blind, I call the big blind");
                            }
                            if (o.getBalance() == 0) {
                                System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                                        + "thing left and my bet is $" + o.getBet());
                            } else {
                                System.out.println(o.getName() + ": Call");
                                System.out.println(o.getName() + ": My bet increases TO $" + o.getBet()
                                        + " cause I called, increasing BY $" + callAmt);
                            }

                            System.out.println(o.getName() + ": The table's pot is now $" + table1.getPot()
                                    + " after I added $" + callAmt);
                            System.out.println(o.getName() + ": The table's bet is now $" + table1.getCurrentBetAmt());
                            o.setIsChecked(true);
                            numPlayersChecked++;
                            previousAction = "Check";
                        } else { // Check
                            if (o.getIsBigBlind() && !afterRound1 && !o.getIsBlindPaid()) {
                                System.out.println(o.getName() + ": Check, I check the big blind");
                            } else {
                                System.out.println(o.getName() + ": Check");
                            }

                            o.setIsChecked(true);
                            numPlayersChecked++;
                            previousAction = "Check";
                        }
                    } else if (botAction == 2) {

                        PlayerBot pb = (PlayerBot) o;
                        int bettingAmount = PlayerBot.getBotRaiseAmt(pb.getTightness(),
                                HandEval.getHandValue(pb.getHand(), table1.getCommCards()), o); // -> how much bot wants to
                                                                                             // increase TO
                        System.out.println(
                                o.getName() + ": I want to bet $" + bettingAmount + " because my cards' value is "
                                        + HandEval.getHandValue(pb.getHand(), table1.getCommCards())); // REMOVE

                        // If the amount to bet is almost the balance, might as well all in
                        if (bettingAmount >= 0.75 * o.getBalance()) { 
                            bettingAmount = o.getBalance();
                        }

                        if (bettingAmount >= o.getBalance()) {// Not enough to bet/raise what YOU want-> All in
                            System.out.println("Oh, I don't have enough, guess I'll all in");
                            bettingAmount = o.getBalance();
                            if (o.getBet() + bettingAmount > table1.getCurrentBetAmt()) { // All in increases table bet
                                table1.raiseCurrentBetAmt(bettingAmount);
                            } // else, don't need change table bet
                            o.raiseBet(bettingAmount);
                            table1.raisePot(bettingAmount);

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
                        } else {
                            System.out.println(o.getName() + ": My bet increases TO $" + o.getBet()
                                    + " cause I increased BY" + bettingAmount);
                        }
                        System.out.println(o.getName() + ": The table's pot is now $" + table1.getPot()
                                + " after I added $" + bettingAmount);
                        System.out.println(o.getName() + ": The table's bet is now $" + table1.getCurrentBetAmt());

                        // DON'T REMOVE BELOW
                        // o.setChecked(true);
                        // numPlayersChecked = 1;
                        // for (Player e : currentPlayers) {
                        // if (!e.equals(o)) {
                        // e.setChecked(false);
                        // }
                        // }
                        // DON'T REMOVE ABOVE
                        for (Player e : currentPlayers) {
                            e.setIsChecked(false);
                        }
                        o.setIsChecked(true);
                        numPlayersChecked = 1;
                        previousAction = "Bet";
                    } else if (botAction == 3) {
                        System.out.println(o.getName() + ": I'm folding");
                        o.setIsPlaying(false);
                        previousAction = "Fold";
                    }

                    if (!afterRound1 && (o.getIsBigBlind() || o.getIsSmallBlind()) && !o.getIsBlindPaid()) {
                        o.setIsBlindPaid(true);
                    }
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

    public static boolean timeToOpenHand(ArrayList<Player> playersList, Table table1, Deck deck1, int numTimesBet) {
        boolean isOpenHandTime = false;
        int bankruptCounter = 0;
        int activePlayerCounter = 0;
        for (Player g : playersList) {
            if (g.getIsPlaying()) {
                activePlayerCounter++;
                if (g.getBalance() == 0) {
                    bankruptCounter++;
                }
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

            GameTextDisplay.printCard(table1.getCommCards());
            ArrayList<Card> showDownCards = new ArrayList<Card>();
            for (Player h : playersList) {
                if (h.getIsPlaying()) {
                    switch (h.getName().length()) {
                        case 6:
                            System.out.print(" " + h.getName() + "                   ");
                            break;
                        case 5:
                            System.out.print(" " + h.getName() + "                    ");
                            break;
                        case 4:
                            System.out.print(" " + h.getName() + "                     ");
                            break;
                        default:
                            System.out.print(" " + h.getName() + "                      ");
                            break;
                    }
                    showDownCards.addAll(h.getHand());

                }
            }
            System.out.println();
            GameTextDisplay.printCard(showDownCards);
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
            o.setIsPlaying(true);
            o.setBet(0);
        }
        table1.getCommCards().clear();
        table1.setCurrentBetAmt(0);
        table1.setPot(0);
    }
}