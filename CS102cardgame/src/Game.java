
//package CS102cardgame.src;

import java.util.*;

public class Game {

    /** The maximum number of community cards. */
    private InputHandler inputHandler;
   

    public Game(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    
    public static int getNumBot() {
        int numberOfPlayers = 0;
        while (true) {
            try {
                System.out.print("Please enter the number of desired bots> ");
                Scanner playerNumbers = new Scanner(System.in);
                if (playerNumbers.hasNextInt()) {
                    numberOfPlayers = playerNumbers.nextInt();
                    if (numberOfPlayers <= 0) {
                        throw new InputMismatchException("There must be at least 1 bot!");
                    }
                    if (numberOfPlayers >= 10) {
                        throw new InputMismatchException("Too many! You can have at most 9 bots");
                    }
                    return numberOfPlayers;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void bettingRound(ArrayList<Player> playersList, Table table1, boolean afterRound1,
            Player[] turnOrder, int numTimesBet) {
        ArrayList<Player> current = new ArrayList<Player>();
        // active users
        for (Player o : turnOrder) {
            if (o.getIsPlaying() == true) {
                current.add(o);
            }
        }

        int counter = 0;
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

        while (counter != getCurrentSize(current)) {
            for (Player o : current) {
                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {
                    System.out.println("Betting Phase: " + bettingRoundName);
                    String action = null;
                    showPlayerAttributes(o, table1, afterRound1);

                    // If Big Blind
                    if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) {
                        System.out.println("Pot: Big Blind! That's $10 thanks");

                        while (true) {
                            try {
                                System.out.println("Check / Bet / Fold");
                                System.out.print("Action> ");
                                action = inputHandler.getInput();
                                if (action.equals("call") || action.equals("raise")) {
                                    throw new InputMismatchException("You cannot " + action + "!");
                                }
                                o.setBlinded(true);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(e.getMessage());
                            }
                        }

                        // If Small Blind
                    } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
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
                                o.setBlinded(true);
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    // Normal betting round
                    } else {

                        // If broke
                        if (o.getBalance() == 0) {
                            // System.out.println("Check /       / Fold");
                            // System.out.print("Action> ");
                            // action = getInput();
                            while (true) {
                                try {
                                    System.out.println("Check /       / Fold");
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

                                            if (action.equals("check") || action.equals("raise") || action.equals("bet")) {
                                                throw new InputMismatchException("You cannot " + action + "!");
                                            }
                                            break;
        
                                        // Enough to call OR raise beyond
                                        } else {
                                            System.out.println("Call / Raise / Fold");
                                            System.out.print("Action> ");
                                            action = inputHandler.getInput();

                                            if (action.equals("check") ||  action.equals("bet")) {
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
                        o.setChecked(true);
                        counter++;
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
                        o.setChecked(true);
                        counter++;
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
                            // table1.raiseCurrentBet(newBet - table1.getCurrentBetAmt()); // TableBet
                            // increased by X
                            table1.setCurrentBet(newBet);
                        }

                        if (o.getBet() != 0) {
                            newBet = newBet - o.getBet();
                        }
                        o.raiseBet(newBet); // Bet increased by X and Balance decreased by X
                        table1.raisePot(newBet); // Pot increased by X

                        o.setChecked(true);
                        counter = 1;
                        for (Player e : current) {
                            if (!e.equals(o)) {
                                e.setChecked(false);
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
                        // counter--;
                    }

                } else if (o.getType().equals("Bot") && o.getChecked() == false && o.getIsPlaying() == true) {
                    // table1.getCurrentBetAmt());
                    // Card.printCard(o.getHand());

                    // THIS IS ONLY HERE FOR DEBUGGING DO NOT DELETE OR CLEAN
                    // Once game is live, remove showPlayerAttributes for bots
                    // AND add small and big blind changer for bot
                    showPlayerAttributes(o, table1, afterRound1);
                    // UNCOMMENT THESE WHEN LIVE DO NOT DELETE OR CLEAN -> hopefully makes bots pay
                    // the big/small blind
                    // if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) {
                    // o.raiseBet(10);
                    // table1.raiseCurrentBet(10);
                    // table1.raisePot(10);
                    // } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
                    // o.raiseBet(5);
                    // table1.raisePot(5);
                    // }

                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) { // Call
                            if (o.getBalance() == 0) { // Already broke
                                System.out.println("Check, I've all in-ed");
                            } else { // Not broke yet
                                int callAmt = table1.getCurrentBetAmt() - o.getBet();
                                if (callAmt > o.getBalance()) {
                                    callAmt = o.getBalance();
                                }
                                o.raiseBet(callAmt);
                                table1.raisePot(callAmt);
                            }

                            int callAmt = table1.getCurrentBetAmt() - o.getBet();
                            if (callAmt > o.getBalance() && o.getBalance() != 0) {
                                callAmt = o.getBalance();
                            }
                            o.raiseBet(callAmt);
                            table1.raisePot(callAmt);

                            if (callAmt == 5 && !afterRound1 && o.getSmallBlind()) {
                                System.out.println(o.getName() + ": Small blind, I call the big blind");
                            }
                            if (o.getBalance() == 0) {
                                System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                                        + "thing left and my bet is $" + o.getBet());
                            } else {
                                System.out.println(o.getName() + ": Call");
                                System.out.println(o.getName() + ": My bet increases TO $" + o.getBet() + " cause I called, increasing BY $"+ callAmt);
                            }

                            System.out.println(o.getName() + ": The table's pot is now $" + table1.getPot()
                                    + " after I added $" + callAmt);
                            System.out.println(o.getName() + ": The table's bet is now $" + table1.getCurrentBetAmt());
                            o.setChecked(true);
                            counter++;
                            previousAction = "Check";
                        } else { // Check
                            if (o.getBigBlind() && !afterRound1) {
                                System.out.println(o.getName() + ": Check, I check the big blind");
                            } else {
                                System.out.println(o.getName() + ": Check");
                            }

                            o.setChecked(true);
                            counter++;
                            previousAction = "Check";
                        }
                    } else if (botAction == 2) {

                        PlayerBot pb = (PlayerBot) o;
                        int bettingAmount = PlayerBot.getBotRaiseAmt(pb.getTightness(),
                                Hand2.getHandValue(pb.getHand(), table1.getCommCards()), o); // -> how much bot wants to
                                                                                             // increase TO
                        System.out.println(
                                o.getName() + ": I want to bet $" + bettingAmount + " because my cards' value is "
                                        + Hand2.getHandValue(pb.getHand(), table1.getCommCards())); // REMOVE

                        if (bettingAmount >= o.getBalance()) {// Not enough to bet/raise what YOU want-> All in
                            System.out.println("Oh, I don't have enough, guess I'll all in");
                            bettingAmount = o.getBalance();
                            if (o.getBet() + bettingAmount > table1.getCurrentBetAmt()) { // All in increases table bet
                                table1.raiseCurrentBet(bettingAmount);
                            } // else, don't need change table bet
                            o.raiseBet(bettingAmount);
                            table1.raisePot(bettingAmount);

                        } else { // Enough to bet/raise what YOU want
                            table1.setCurrentBet(bettingAmount);
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

                        // if (bettingAmount <= table1.getCurrentBetAmt()) { // if raise TO < table bet,
                        // adjust accordingly
                        // bettingAmount = table1.getCurrentBetAmt() + 10;
                        // }

                        // if (bettingAmount > pb.getBalance()){ // If balance not enough to raise TO
                        // bettingAmount = pb.getBalance(); // Commit the rest of balance
                        // }

                        // int increasedBettingAmount = bettingAmount - o.getBet();
                        // if (bettingAmount == pb.getBalance()) {
                        // increasedBettingAmount = 0;
                        // }

                        // o.raiseBet(increasedBettingAmount);

                        // if (bettingAmount == pb.getBalance()) {
                        // table1.raiseCurrentBet(increasedBettingAmount);
                        // } else {
                        // table1.setCurrentBet(bettingAmount);
                        // }

                        // table1.raisePot(increasedBettingAmount);

                        // DON'T REMOVE BELOW
                        // o.setChecked(true);
                        // counter = 1;
                        // for (Player e : current) {
                        // if (!e.equals(o)) {
                        // e.setChecked(false);
                        // }
                        // }
                        // DON'T REMOVE ABOVE
                        for (Player e : current) {
                            e.setChecked(false);
                        }
                        o.setChecked(true);
                        counter = 1;
                        previousAction = "Bet";
                    } else if (botAction == 3) {
                        System.out.println(o.getName() + ": I'm folding");
                        o.setIsPlaying(false);
                        previousAction = "Fold";
                    }
                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (getCurrentSize(current) == 1) {
                    return;
                }

            }

        }

        // ????
        for (Player o : playersList) {
            o.setChecked(false);
        }
        System.out.println("This concludes the betting for " + bettingRoundName + " phase");
        System.out.println();
    }

    public static void showPlayerAttributes(Player o, Table table1, Boolean afterRound1) {
        String playerName = o.getName();
        System.out.println(playerName + " hand: ");
        Card.printCard(o.getHand());
        System.out.println("┌────────────────┐──────┐");
        switch (playerName.length()) {
            case 6:
                System.out.print("| " + playerName + " balance | ");
                break;
            case 5:
                System.out.print("| " + playerName + " balance  | ");
                break;
            case 4:
                System.out.print("| " + playerName + " balance   | ");
                break;
            default:
                System.out.print("| " + playerName + " balance    | ");
                break;
        }
        if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) { // Not chen round, big blind, and not blinded yet
            o.raiseBet(10);
            table1.raiseCurrentBet(10);
            table1.raisePot(10);
            System.out.print(o.getBalance());

        } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
            o.raiseBet(5);
            table1.raisePot(5);
            System.out.print(o.getBalance());

        } else {
            System.out.print(o.getBalance());
        }
        if (o.getBalance() >= 1000) {
            System.out.println(" |");
        } else if (o.getBalance() >= 100) {
            System.out.println("  |");
        } else if (o.getBalance() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.print("| Your bet       | " + o.getBet());
        if (o.getBet() >= 1000) {
            System.out.println(" |");
        } else if (o.getBet() >= 100) {
            System.out.println("  |");
        } else if (o.getBet() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) {
            System.out.println("| Big Blind      | 10   |");
        } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
            System.out.println("| Small Blind    | 5    |");
        }
        System.out.print("| Table's pot    | " + table1.getPot());
        if (table1.getPot() >= 1000) {
            System.out.println(" |");
        } else if (table1.getPot() >= 100) {
            System.out.println("  |");
        } else if (table1.getPot() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.print("| Table's Bet    | " + table1.getCurrentBetAmt());
        if (table1.getCurrentBetAmt() >= 1000) {
            System.out.println(" |");
        } else if (table1.getCurrentBetAmt() >= 100) {
            System.out.println("  |");
        } else if (table1.getCurrentBetAmt() >= 10) {
            System.out.println("   |");
        } else {
            System.out.println("    |");
        }
        System.out.println("└────────────────┘──────┘");
    }

    public static int getCurrentSize(ArrayList<Player> current) {
        int counter = 0;
        for (Player o : current) {
            if (o.getIsPlaying()) {
                counter++;
            }
        }
        return counter;
    }

    public static Boolean timeToOpenHand(ArrayList<Player> playersList, Table table1, Deck deck1, int numTimesBet) {
        Boolean isOpenHandTime = false;
        int openHandCounter = 0;
        int activePlayerCounter = 0;
        for (Player g : playersList) {
            if (g.getIsPlaying()) {
                activePlayerCounter++;
                if (g.getBalance() == 0) {
                    openHandCounter++;
                }
            }
        }
        if (openHandCounter >= activePlayerCounter - 1 || activePlayerCounter == 1) {
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

            Card.printCard(table1.getCommCards());
            ArrayList<Card> showDownCards = new ArrayList<Card>();
            for (Player h : playersList) {
                if (h.getIsPlaying()) {
                    switch (h.getName().length()) {
                        case 6:
                            System.out.print(" " + h.getName()+ "                   ");
                            break;
                        case 5:
                            System.out.print(" " + h.getName()+ "                    ");
                            break;
                        case 4:
                            System.out.print(" " + h.getName()+ "                     ");
                            break;
                        default:
                            System.out.print(" " + h.getName()+ "                      ");
                            break;
                    }
                    showDownCards.addAll(h.getHand());

                }
            }
            System.out.println();
            Card.printCard(showDownCards);
            Winner.getWinner(playersList, table1);

            System.out.println("Round over");

            return true;
        }
        return false;
    }

    public static Boolean isWinLose(ArrayList<Player> playersList) {
        // int allWinning = userPlayer.getBalance() * playersList.size();
        int loser = 0;
        for (Player p : playersList) { // Player win
            if (!p.getType().equals("Player")) {
                if (p.getBalance() == 0) {
                    loser++;
                }
            }
            // Everyone lose to you You lost to everyone
            if (loser == playersList.size() - 1 || (p.getType().equals("Player") && p.getBalance() == 0)) {
                return true;
            }
        }

        return false;
    }

    public static void resetRound(ArrayList<Player> playersList, Table table1) {
        for (Player o : playersList) {
            o.setChecked(false);
            o.getHand().clear();
            o.setIsPlaying(true);
            o.setBet(0);
        }
        table1.getCommCards().clear();
        table1.setCurrentBet(0);
        table1.setPot(0);
    }

    public static void moveBlind(Player[] turnOrder) {
        Player temp = turnOrder[0];
        for (int i = 1; i < turnOrder.length; i++) {
            turnOrder[i - 1] = turnOrder[i];
            turnOrder[i].setBlinded(false);
            turnOrder[i].setBigBlind(false);
            turnOrder[i].setSmallgBlind(false);
        }

        turnOrder[turnOrder.length - 1] = temp;
        turnOrder[0].setBigBlind(true);
        turnOrder[1].setSmallgBlind(true);
    }

}
