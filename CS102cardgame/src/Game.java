
//package CS102cardgame.src;

import java.util.*;

public class Game {

    /** The maximum number of community cards. */
    private static final int NO_OF_CARDS = 5;

    public Game() {
    }

    public static void main(String[] args) {
        ArrayList<Player> playersList = new ArrayList<Player>();

        int numberOfPlayers = 0;

        Scanner playerNumbers = new Scanner(System.in);
        // Keeps prompting for number of players to start game
        while (true) {
            try {
                if (playerNumbers.hasNextInt()) {
                    numberOfPlayers = playerNumbers.nextInt();
                    if (numberOfPlayers < 0) {
                        throw new InputMismatchException("");
                    }
                }
                System.out.print("Enter number of bots> ");
                numberOfPlayers = playerNumbers.nextInt();
                break;
            } catch (InputMismatchException ime) {
                System.out.println("Please enter a valid number");
            } finally {
                playerNumbers.nextLine();
            }
        }

        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    inputBet = sc.nextInt();
                    if (inputBet > p.getBalance()) {
                        throw new InputMismatchException("Insufficient Balance, Enter new bet> ");
                    }
                    return inputBet;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage());
            }
        }
        // playerNumbers.close();

        // Initialise a player that user controls and add into list of players
        Player userPlayer = new Player("Tom", "Player");
        playersList.add(userPlayer);
        Player userPlayer1 = new PlayerBot("Nic", "Bot");
        playersList.add(userPlayer1);
        Player userPlayer2 = new PlayerBot("Ken", "Bot");
        playersList.add(userPlayer2);

        int originalBalance = userPlayer.getBalance();
        Player[] turnOrder = { userPlayer, userPlayer1, userPlayer2 };

        // Initialise number of bots and add them into list of players
        // for (int i = 0; i < 3; i++) {
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        boolean gameContinue = true;

        // startingScreen();
        int gameCounter = 1;
        int bigBlind = 10;
        int smallBlind = 5;
        userPlayer.setBigBlind(true);

        while (gameContinue) {
            int numTimesBet = 0;
            boolean afterRound1 = false;
            System.out.println("Game " + gameCounter++ + "!");

            Table table1 = new Table();
            Deck deck1 = new Deck();

            Game game = new Game();

            // Shuffle Deck
            Collections.shuffle(deck1.getCards());

            // Cut Deck
            // Scanner getCutNum = new Scanner (System.in);
            // deck1.cutDeck();

            // deal cards
            for (Player e : playersList) {
                e.draw(deck1.dealCard());
                e.draw(deck1.dealCard());
            }

            // 1st Betting Round
            bettingRound(playersList, table1, afterRound1, turnOrder);
            if (timeToOpenHand(playersList, table1, deck1, ++numTimesBet)) {
                resetRound(playersList, table1); // Resets player and table attributes

                if (isWinLose(playersList)) { // If you win all or lost all, game ends.
                    gameContinue = false;
                    break;
                }

                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    // Reset everything
                    moveBlind(turnOrder);
                    continue;
                }

                continue;
            }
            afterRound1 = true;
            

            // Deal Flop (3 cards)
            for (int i = 0; i < 3; i++) {
                deck1.burnCard();
                table1.drawComm(deck1.dealCard());
            }
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // 2nd Betting Round
            bettingRound(playersList, table1, afterRound1, turnOrder);
            if (timeToOpenHand(playersList, table1, deck1, ++numTimesBet)) {
                resetRound(playersList, table1);

                if (isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }

                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // Deal Turn
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // 3rd Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1, turnOrder);
            if (timeToOpenHand(playersList, table1, deck1, ++numTimesBet)) {
                resetRound(playersList, table1);

                if (isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }
                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // Deal River
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // Last Betting Round
            bettingRound(playersList, table1, afterRound1, turnOrder);
            if (timeToOpenHand(playersList, table1, deck1, ++numTimesBet)) {
                resetRound(playersList, table1);

                if (isWinLose(playersList)) {
                    gameContinue = false;
                    break;
                }
                Scanner scRoundEndallin = new Scanner(System.in);
                System.out.println("Start new round?(Y / N)> ");
                String newRound = scRoundEndallin.nextLine();
                if (newRound.equals("N") || newRound.equals("n")) {
                    gameContinue = false;
                    scRoundEndallin.close();
                } else if (newRound.equals("Y") || newRound.equals("y")) {
                    moveBlind(turnOrder);
                    continue;
                }

                continue;
            }

            // for second game
            System.out.println(table1.getPot());

            getWinner(playersList, table1);
            
            System.out.println("Round over");

            if (isWinLose(playersList)) {
                gameContinue = false;
                break;
            }
            Scanner scRoundEndallin = new Scanner(System.in);
            System.out.println("Start new round?(Y / N)> ");
            String newRound = scRoundEndallin.nextLine();
            if (newRound.equals("N") || newRound.equals("n")) {
                gameContinue = false;
                scRoundEndallin.close();
            } else if (newRound.equals("Y") || newRound.equals("y")) {
                resetRound(playersList, table1);
                moveBlind(turnOrder); // moves big blind small blind, moves order of players
                continue;
            }

            continue;
        }
        System.out.println("Thanks for playing Texas Hold'em!");
        System.out.println("See you next time!");
        if (originalBalance > userPlayer.getBalance() && userPlayer.getBalance() != 0) {
            System.out.println("You lost $" + (originalBalance - userPlayer.getBalance()));
            System.out.println("Better luck next time!");
        } else if (userPlayer.getBalance() == originalBalance * playersList.size()) {
            System.out.println("You won everything!");
        } else if (userPlayer.getBalance() == 0) {
            System.out.println("HAHAHA HOW TF YOU LOSE EVERYTHING TO BOTS");
        } else if (userPlayer.getBalance() == 100) {
            System.out.println("Damn, you didn't win anything? Congrats on wasting your time!");
        } else /* if (originalBalance < userPlayer.getBalance()) */ {
            System.out.println("You won $" + (userPlayer.getBalance() - originalBalance));
        }

    }

    public static void getWinner(ArrayList<Player> playersList, Table table1) {
        Map<Player, Integer> winner = new HashMap<>();

        for (Player o : playersList) {
            if (o.getIsPlaying()) {
                System.out.println("Player " + o.getName() + " hand:");
                Card.printCard(o.getHand());
                winner.put(o, Hand2.getHandValue(o.getHand(), table1.getCommCards()));
                System.out.println();
            }

        }
        int highest = 0;

        for (Player p : winner.keySet()) {
            if (winner.get(p) > highest) {
                highest = winner.get(p);
            }
        }

        ArrayList<Player> winnerList = new ArrayList<Player>();
        System.out.println(highest);
        for (Player player : winner.keySet()) {
            if (winner.get(player) == highest) {
                winnerList.add(player);
            }
        }
        if (winnerList.size() > 1) {
            for (Player o : winnerList) {
                o.setEndBalance(table1.getPot() / winnerList.size());
            }
        } else {
            winnerList.get(0).setEndBalance(table1.getPot());
        }
        for (Player o : winnerList) {
            System.out.println("Winner is " + o.getName());
        }
    }

    public static String getInput() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                String inputCommand = sc.nextLine();
                inputCommand = inputCommand.trim();
                inputCommand = inputCommand.toLowerCase();
                if (inputCommand.equals("check") || inputCommand.equals("fold") || inputCommand.equals("call")) {
                    return inputCommand;
                } else if (inputCommand.contains("bet")) {
                    System.out.print("Bet?> ");
                    return inputCommand;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println("try again");
            }
        }
    }

    public static int getBetInput(Player p) {
        int inputBet;
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()) {
                    inputBet = sc.nextInt();
                    if (inputBet > p.getBalance()) {
                        throw new InputMismatchException("Insufficient Balance, Enter new bet> ");
                    }
                    return inputBet;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public static void bettingRound(ArrayList<Player> playersList, Table table1, boolean afterRound1,
            Player[] turnOrder) {
        ArrayList<Player> current = new ArrayList<Player>();
        for (Player o : turnOrder) {
            if (o.getIsPlaying() == true) {
                current.add(o);
            }
        }
        System.out.println(current);
        // BIG BLIND SMALL BLIND SORTING ORDER
        int counter = 0;
        String previousAction = null;

        while (counter != getCurrentSize(current)) {
            for (Player o : current) {
                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {
                    String action = null;
                    showPlayerAttributes(o, table1, afterRound1);

                    if (o.getBet() < table1.getCurrentBetAmt()) {
                        System.out.println("Call / Raise / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        // o.setChecked(true);
                        // o.setBet(table1.getCurrentBetAmt());
                        // o.setBalance(o.getBet());

                        // int raiseAmt = table1.getCurrentBetAmt() - o.getBet();
                        // o.raiseBet(raiseAmt);
                        // table1.raisePot(raiseAmt);
                        // counter++;
                        // previousAction = "Check";
                    } else if (o.getBalance() == 0) {
                        System.out.println("Check /       / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        // o.setChecked(true);
                        // counter++;
                        // previousAction = "Check";
                    } else if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) {
                        System.out.println("Pot: Big Blind! That's $10 thanks");
                        System.out.println("Check / Bet / Fold");

                        System.out.print("Action> ");
                        action = getInput();
                        o.setBlinded(true);
                        // o.setChecked(true);
                        // counter++;
                        // previousAction = "Check";
                    } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
                        System.out.println("Pot: Small Blind! That's $5 thanks");
                        if (o.getBet() < table1.getCurrentBetAmt()) {       // Someone raised
                            System.out.println("Check / Raise / Fold");
                        } else {                                            // No one raised yet
                            System.out.println("Check / Bet / Fold");
                        }
                        System.out.print("Action> ");
                        action = getInput();
                        o.setBlinded(true);
                        // o.setChecked(true);
                        // counter++;
                        // previousAction = "Check";
                    } else { // o.getBet() == table1.getCurrentBetAmt()
                        System.out.println("Check / Bet / Fold");
                        System.out.print("Action> ");
                        action = getInput();

                        if (o.getBet() < table1.getCurrentBetAmt()) {
                            // o.setBet(table1.getCurrentBetAmt());
                            System.out.println("Something's wrong!!");
                        }
                        // o.setBalance(o.getBet());
                        // o.setChecked(true);
                        // counter++;
                        // previousAction = "Check";
                    }

                    if (action.equals("check")) { // Can only check if bet matches table bet
                        o.setChecked(true);
                        counter++;
                        previousAction = "Check";
                    }
                    if (action.equals("call")) {
                        o.setChecked(true);
                        int raiseAmt = table1.getCurrentBetAmt() - o.getBet();
                        if (raiseAmt > o.getBalance()) {
                            raiseAmt = o.getBalance();
                        }
                        o.raiseBet(raiseAmt);
                        table1.raisePot(raiseAmt);
                        counter++;
                        if (o.getBalance() == 0) {
                            System.out.println("I'm all in baby");
                        }
                        System.out.println("Your new balance: " + o.getBalance());
                        System.out.println("Your new Bet " + o.getBet());
                        System.out.println("Table's new pot " + table1.getPot());
                        previousAction = "Check";
                    }
                    if (action.equals("bet")) {
                        // COLLAPSED
                        // int raisedAmt = newBet - o.getBet();
                        // o.setBet(newBet);
                        // o.setBalance(newBet);
                        // table1.setCurrentBet(newBet);
                        int newBet = getBetInput(o); // Amt X player increases by
                        while (newBet <= table1.getCurrentBetAmt() - o.getBet()){
                            System.out.println("Not enough! You need to raise at least $" + (table1.getCurrentBetAmt() - o.getBet()));
                            newBet = getBetInput(o);
                        }

                        o.raiseBet(newBet); // Bet increased by X and Balance decreased by X
                        table1.raiseCurrentBet(newBet); // TableBet increased by X
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

                    // THIS IS ONLY HERE FOR DEBUGGING
                    // Once game is live, remove showPlayerAttributes for bots
                    // AND add small and big blind changer for bot
                    showPlayerAttributes(o, table1, afterRound1);
                    // UNCOMMENT THESE WHEN LIVE -> should make bots pay the big/small blind
                    // if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) { 
                    //     o.raiseBet(10);
                    //     table1.raiseCurrentBet(10);
                    //     table1.raisePot(10);
                    // } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
                    //     o.raiseBet(5);
                    //     table1.raisePot(5);
                    // }
                    
                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) {
                            o.setChecked(true);
                            int callAmt = table1.getCurrentBetAmt() - o.getBet();
                            if (callAmt > o.getBalance() && o.getBalance() != 0) {
                                callAmt = o.getBalance();
                            }
                            o.raiseBet(callAmt);
                            table1.raisePot(callAmt);
                            // o.setBet(table1.getCurrentBetAmt());
                            // o.setBalance(o.getBet());
                            if (callAmt == 5 && !afterRound1 && o.getSmallBlind()) {
                                System.out.println(o.getName() + ": Small blind, checked");
                            }
                            if (o.getBalance() == 0) {
                                System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                                        + "thing left and my bet is $" + o.getBet());
                            } else {
                                System.out.println(o.getName() + ": Call");
                                System.out.println(
                                        o.getName() + ": I have " + o.getBalance() + " and my bet is " + o.getBet());
                            }

                            System.out.println("The table's pot is " + table1.getPot());
                            counter++;
                            previousAction = "Check";
                        } else if (o.getBalance() == 0) {
                            System.out.println("Check, I've all in-ed");
                            o.setChecked(true);
                            counter++;
                            previousAction = "Check";

                        } else { // bot bet = table bet
                            if (o.getBigBlind() && !afterRound1) {
                                System.out.println("I call the big blind");
                            } else {
                                System.out.println(o.getName() + ": Check");
                            }
                            
                            o.setChecked(true);
                            if (o.getBet() != table1.getCurrentBetAmt()) {
                                System.out.println("CUSTOM ERROR: Bot bet != table bet");
                            }
                            // o.setBet(table1.getCurrentBetAmt());
                            // o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Check";
                        }

                    } else if (botAction == 2) {
                        

                        for (Player f : playersList) {
                            if (!(f.getType().equals("Player")) && !(f.equals(o))) {
                                if (f.getIsPlaying()) {
                                    Random random = new Random();
                                    int randomreply = random.nextInt(5) + 1;
                                    PlayerBot.randomangryreply(f.getName(), o.getName(), randomreply);
                                }
                            }
                        }

                        int newBet = 20; // Bot always adds $20 when betting
                        if (newBet >= o.getBalance()) {
                            newBet = o.getBalance();
                        }
                        System.out.println(o.getName() + ": I'll bet " + newBet /* + (table1.getBet() +10) */); 

                        // int newBet = table1.getCurrentBetAmt() + 10;

                        // o.setBet(newBet);
                        // o.setBalance(newBet);
                        // table1.setCurrentBet(table1.getCurrentBetAmt() + newBet);

                        o.raiseBet(newBet);
                        table1.raiseCurrentBet(newBet);
                        table1.raisePot(newBet);
                        if (o.getBalance() == 0) {
                            System.out.println(o.getName() + ": " + "ALL IN BABY. I got N" + o.getBalance()
                            + "thing left and my bet is $" + o.getBet());
                        } else {
                            System.out.println(o.getName() + ": I have " + o.getBalance() + " and my bet is " + o.getBet());
                        }
                        
                        System.out.println("The current table bet amount is " + table1.getCurrentBetAmt());
                        System.out.println("The Table's Pot is " + table1.getPot());

                        o.setChecked(true);
                        counter = 1;
                        for (Player e : current) {
                            if (!e.equals(o)) {
                                e.setChecked(false);
                            }
                        }
                        previousAction = "Bet";
                        // System.out.println("After Bet " + o.getBet());
                        // System.out.println("After Balance " + o.getBalance());
                        // System.out.println("After Table Bet " + table1.getCurrentBetAmt());

                    } else if (botAction == 3) {
                        System.out.println(o.getName() + ": I'm folding");
                        o.setIsPlaying(false);
                        // counter++;
                        // current.remove(o);
                        previousAction = "Fold";
                    }

                }

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // table1.setPot(o.getBet());
                // o.setBet(0);

            }

        }
        for (Player o : playersList) {
            o.setChecked(false);
            // if (o.getType().equals("Player")) {
            // // o.setBet(0);
            // }
        }
        // table1.setCurrentBet(0);
        // Move BIG BLIND SMALL BLIND
    }

    public static void showPlayerAttributes(Player o, Table table1, Boolean afterRound1) {
        System.out.println(o.getName() + " hand: ");
        Card.printCard(o.getHand());
        System.out.println("┌───────────────┐─────┐");
        System.out.print("|  " + o.getName() + " balance  | ");
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
        if (o.getBalance() >= 100) {
            System.out.println(" |");
        } else if (o.getBalance() >= 10) {
            System.out.println("  |");
        } else {
            System.out.println("   |");
        }
        System.out.print("| Your bet      | " + o.getBet());
        if (o.getBet() >= 100) {
            System.out.println(" |");
        } else if (o.getBet() >= 10) {
            System.out.println("  |");
        } else {
            System.out.println("   |");
        }
        if (!afterRound1 && o.getBigBlind() && !o.getBlinded()) {
            System.out.println("| Big Blind     | 10  |");
        } else if (!afterRound1 && o.getSmallBlind() && !o.getBlinded()) {
            System.out.println("| Small Blind   | 5   |");
        }
        System.out.print("| Table's pot   | " + table1.getPot());
        if (table1.getPot() >= 100) {
            System.out.println(" |");
        } else if (table1.getPot() >= 10) {
            System.out.println("  |");
        } else {
            System.out.println("   |");
        }
        System.out.print("| Table's Bet   | " + table1.getCurrentBetAmt());
        if (table1.getCurrentBetAmt() >= 100) {
            System.out.println(" |");
        } else if (table1.getCurrentBetAmt() >= 10) {
            System.out.println("  |");
        } else {
            System.out.println("   |");
        }
        System.out.println("└───────────────┘─────┘");
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
        if (openHandCounter == activePlayerCounter || activePlayerCounter == 1) {
            isOpenHandTime = true;
            System.out.println("OPENHAND");
        } else {
            return false;
        }

        if (isOpenHandTime) {
            System.out.println("Everybody open hand!");
            for (int i = 0; i < 4 - numTimesBet; i++) {
                deck1.burnCard();
                table1.drawComm(deck1.dealCard());
            }
            Card.printCard(table1.getCommCards());
            for (Player h : playersList) {
                if (h.getIsPlaying()) {
                    System.out.println(h.getName());
                    Card.printCard(h.getHand());
                }
            }
            getWinner(playersList, table1);

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

    public static void startingScreen() {

        System.out.println("                                                                       Welcome to");
        System.out.println(
                "                                                        .------..------..------..------..------.          ");
        System.out.println(
                "                                                        |T.--. ||E.--. ||X.--. ||A.--. ||S.--. |          ");
        System.out.println(
                "                                                        | :/\\: || (\\/) || :/\\: || (\\/) || :/\\: |          ");
        System.out.println(
                "                                                        | (__) || :\\/: || (__) || :\\/: || :\\/: |          ");
        System.out.println(
                "                                                        | '--'T|| '--'E|| '--'X|| '--'A|| '--'S|          ");
        System.out.println(
                "                                                        `------'`------'`------'`------'`------'          ");
        System.out.println(
                "                                                .------..------..------..------..------..------..------.");
        System.out.println(
                "                                                |H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |");
        System.out.println(
                "                                                | :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |");
        System.out.println(
                "                                                | (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |");
        System.out.println(
                "                                                | '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|");
        System.out.println(
                "                                                `------'`------'`------'`------'`------'`------'`------'");
        System.out.print("Shuffling Cards ");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(". ");
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Stacking Chips ");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(". ");
        }

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.print("Loading ");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(Character.toChars(0x2593));
        }
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(" 100%");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();
    }

}
