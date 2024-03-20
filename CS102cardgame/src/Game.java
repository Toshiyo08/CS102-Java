
//package CS102cardgame.src;

import java.util.*;

public class Game {

    /** The maximum number of community cards. */
    private static final int NO_OF_CARDS = 5;

    public Game() {
    }

    public static void main(String[] args) {

        // Initiliase an Arraylist to store number of players
        ArrayList<Player> playersList = new ArrayList<Player>();

        int numberOfPlayers = 0;

        // Scanner playerNumbers = new Scanner(System.in);
        // // Keeps prompting for number of players to start game
        // while (true) {
        // try {
        // System.out.println("Enter number of players: ");
        // numberOfPlayers = playerNumbers.nextInt();
        // break;
        // } catch (InputMismatchException ime) {
        // System.out.println("Please enter valid number");
        // } finally {
        // playerNumbers.nextLine();
        // }
        // }
        // playerNumbers.close();

        // Initialise a player that user controls and add into list of players
        Player userPlayer = new Player("Tom", "Player");
        playersList.add(userPlayer);
        Player userPlayer1 = new PlayerBot("Nic", "Bot");
        playersList.add(userPlayer1);
        Player userPlayer2 = new PlayerBot("Ken", "Bot");
        playersList.add(userPlayer2);

        int originalBalance = userPlayer.getBalance();

        // Initialise number of bots and add them into list of players
        // for (int i = 0; i < 3; i++) {
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        boolean gameContinue = true;
        int allWinning = userPlayer.getBalance() * playersList.size();
        // startingScreen();
        Boolean isOpenHandTime = false;
        int roundCounter = 1;

        while (gameContinue) {
            System.out.println("Round " + roundCounter++ + "!");

            Table table1 = new Table();
            // Create Deck
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
            userPlayer.setBigBlind(true);
            // for (Player p : playersList) {
            // System.out.println(p.getBalance());
            // }
            deck1.burnCard();

            boolean afterRound1 = false;
            // 1st Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1); // DONT DELETE THIS LINE

            // System.out.println(table1.getPot());
            // for (Player p : playersList) {
            // System.out.println(p.getBalance());
            // }

            // Deal Flop (3 cards)
            for (int i = 0; i < 3; i++) {
                table1.drawComm(deck1.dealCard());
            }

            // for (Card c : table1.getCommCards()) {
            // System.out.println(c);
            // }
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);
            afterRound1 = true;

            // 2nd Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1);
            if (timeToOpenHand(isOpenHandTime, playersList, table1, deck1)) {
                userPlayer.setIsPlaying(true);
                userPlayer1.setIsPlaying(true);
                userPlayer2.setIsPlaying(true);
                for (Player o : playersList) {
                    o.getHand().clear();
                }
                table1.getCommCards().clear();
                for (Player p : playersList) {
                    if (p.getBalance() == allWinning) {
                        gameContinue = false;
                        break;
                    }
                }
                if (userPlayer.getBalance() == 0) {
                    gameContinue = false;
                    break;
                }

                continue;
            }

            // System.out.println(table1.getPot());
            // for (Player p : playersList) {
            // System.out.println(p.getBalance());
            // }
            // Deal Turn------------------------------
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());

            // for (Card c : table1.getCommCards()) {
            // System.out.println(c);
            // }
            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // 3rd Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1);
            // System.out.println(table1.getPot());
            // for (Player p : playersList) {
            // System.out.println(p.getBalance());
            // }
            // Deal River
            deck1.burnCard();
            table1.drawComm(deck1.dealCard());

            // for (Card c : table1.getCommCards()) {
            // System.out.println(c);
            // }

            Card.printCard(table1.getCommCards());

            // userPlayer.setChecked(false);
            // userPlayer1.setChecked(false);
            // userPlayer2.setChecked(false);
            // userPlayer.setBet(0);
            // table1.setCurrentBet(0);

            // Last Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1);

            // for second game
            System.out.println(table1.getPot());
            Map<Player, Integer> winner = new HashMap<>();

            for (Player o : playersList) {
                if (o.getIsPlaying()) {
                    System.out.println("Player " + o.getName() + " hand:");
                    // for (Card c : o.getHand()) {
                    // System.out.println(c);
                    // }
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

            // Last Betting
            // Round---------------------------------------------------------------------------
            System.out.println("Round over");

            for (Player p : playersList) {
                if (p.getBalance() == allWinning) {
                    gameContinue = false;
                    break;
                }
            }
            if (userPlayer.getBalance() == 0) {
                gameContinue = false;
                break;
            }
            Scanner scRoundEnd = new Scanner(System.in);
            System.out.println("Start new round?(Y / N)> ");
            String newRound = scRoundEnd.nextLine();
            if (newRound.equals("N") || newRound.equals("n")) {
                gameContinue = false;
                scRoundEnd.close();
            } else if (newRound.equals("Y") || newRound.equals("y")) {
                // Reset everything
                userPlayer.setIsPlaying(true);
                userPlayer1.setIsPlaying(true);
                userPlayer2.setIsPlaying(true);
                for (Player o : playersList) {
                    o.getHand().clear();
                }
                table1.getCommCards().clear();
            }
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
                    System.out.println("Bet?> ");
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
                        throw new InputMismatchException("Insufficient Balance, Enter new bet");
                    }
                    return inputBet;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void bettingRound(ArrayList<Player> playersList, Table table1, boolean afterRound1) {
        ArrayList<Player> current = new ArrayList<Player>();
        for (Player o : playersList) {
            if (o.getIsPlaying() == true) {
                current.add(o);
            }
        }
        // BIG BLIND SMALL BLIND SORTING ORDER
        int counter = 0;
        String previousAction = null;

        while (counter != getCurrentSize(current)) {
            for (Player o : current) {

                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {
                    String action = null;
                    System.out.println("Your hand: ");
                    Card.printCard(o.getHand());
                    System.out.println("┌───────────────┐─────┐");
                    System.out.print("| Your balance  | ");
                    if (!afterRound1 && o.getBigBlind()) {
                        o.raiseBet(10);
                        table1.raiseCurrentBet(10);
                        table1.raisePot(10);
                        System.out.print(o.getBalance());
                    } else if (!afterRound1 && o.getSmallBlind()) {
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
                    System.out.print("| Your bet      | ");
                    if (!afterRound1 && o.getBigBlind()) {
                        System.out.print(o.getBet());
                    } else if (!afterRound1 && o.getSmallBlind()) {
                        System.out.print(o.getBet());
                    } else {
                        System.out.print(o.getBet());
                    }
                    if (o.getBet() >= 100) {
                        System.out.println(" |");
                    } else if (o.getBet() >= 10) {
                        System.out.println("  |");
                    } else {
                        System.out.println("   |");
                    }
                    System.out.print("| Table's pot   | " + table1.getPot());
                    if (table1.getPot() >= 100) {
                        System.out.println(" |");
                    } else if (table1.getPot() >= 10) {
                        System.out.println("  |");
                    } else {
                        System.out.println("   |");
                    }
                    System.out.println("└───────────────┘─────┘");

                    if (o.getBet() < table1.getCurrentBetAmt()) {
                        System.out.println("Call / Raise / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        // o.setBet(table1.getCurrentBetAmt());
                        // o.setBalance(o.getBet());
                        int raiseAmt = table1.getCurrentBetAmt() - o.getBet();
                        o.raiseBet(raiseAmt);
                        table1.raisePot(raiseAmt);
                        counter++;
                        previousAction = "Call";
                    } else if (o.getBalance() == 0) {
                        System.out.println("Check /       / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        counter++;
                        previousAction = "Check";
                    } else if (!afterRound1 && o.getBigBlind()) {
                        System.out.println("Call Big Blind / Bet / Fold");
                        System.out.println("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        counter++;
                        previousAction = "Check";
                    } else if (!afterRound1 && o.getSmallBlind()) {
                        System.out.println("Call / Bet / Fold");
                        System.out.println("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        counter++;
                        previousAction = "Check";
                    } else {
                        System.out.println("Check / Bet / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        o.setBet(table1.getCurrentBetAmt());
                        // o.setBalance(o.getBet());
                        counter++;
                        previousAction = "Check";
                    }

                    if (action.equals("bet")) {

                        int newBet = getBetInput(o);
                        // o.setBet(newBet);
                        // o.setBalance(newBet);
                        o.raiseBet(newBet);
                        System.out.println("Your new balance: " + o.getBalance());
                        System.out.println("Your new Bet " + o.getBet());

                        // table1.setCurrentBet(newBet);
                        table1.raiseCurrentBet(newBet);
                        table1.raisePot(newBet);
                        System.out.println("Table's new pot " + table1.getPot());
                        o.setChecked(true);
                        counter = 1;
                        for (Player e : current) {
                            if (!e.equals(o)) {
                                e.setChecked(false);
                            }
                        }
                        previousAction = "Bet";

                    }
                    if (action.equals("fold")) {

                        o.setIsPlaying(false);
                        previousAction = "Fold";
                        counter--;

                    }
                } else if (o.getType().equals("Bot") && o.getChecked() == false && o.getIsPlaying() == true) {
                    System.out.println(o.getName() + ": I have " + o.getBalance() + " and my bet is " + o.getBet());
                    // botLogic(o); // void method, returns nothing
                    // promtBotLogic(o) -> checks their hand+commCard points -> returns action int
                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) {

                            o.setChecked(true);
                            int callAmt = table1.getCurrentBetAmt() - o.getBet();
                            o.raiseBet(callAmt);
                            table1.raisePot(callAmt);
                            // o.setBet(table1.getCurrentBetAmt());
                            // o.setBalance(o.getBet());
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
                            previousAction = "Call";
                        } else if (o.getBalance() == 0) {
                            System.out.println("Check, I've all in-ed");
                            o.setChecked(true);
                            counter++;
                            previousAction = "Check";

                        } else {
                            System.out.println(o.getName() + ": Check");
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Check";
                        }

                    } else if (botAction == 2) {
                        System.out.println(o.getName() + ": I'll bet 5");

                        for (Player f : playersList) {
                            if (!(f.getType().equals("Player")) && !(f.equals(o))) {
                                if (f.getIsPlaying()) {
                                    Random random = new Random();
                                    int randomreply = random.nextInt(5) + 1;
                                    PlayerBot.randomangryreply(f.getName(), o.getName(), randomreply);
                                }
                            }
                        }

                        int newBet = 5;
                        // System.out.println("Before Bet " + o.getBet());
                        // System.out.println("Before Balance " + o.getBalance());
                        // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());

                        // o.setBet(newBet);
                        // o.setBalance(newBet);

                        o.raiseBet(newBet);
                        System.out.println(o.getName() + ": I have " + o.getBalance() + " and my bet is " + o.getBet());
                        // table1.setCurrentBet(newBet);
                        table1.raiseCurrentBet(newBet);
                        table1.raisePot(newBet);
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
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(1000);
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
            // o.setBet(0);
            // }
        }
        // table1.setCurrentBet(0);
        // Move BIG BLIND SMALL BLIND
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

    public static Boolean timeToOpenHand(Boolean isOpenHandTime, ArrayList<Player> playersList, Table table1,
            Deck deck1) {
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
            for (int i = 0; i < 3; i++) {
                deck1.burnCard();
                table1.drawComm(deck1.dealCard());
            }
            Card.printCard(table1.getCommCards());
            for (Player h : playersList) {
                if (h.getIsPlaying()) {
                    Card.printCard(h.getHand());
                }
            }

            Map<Player, Integer> winner = new HashMap<>();

            for (Player o : playersList) {
                if (o.getIsPlaying()) {
                    System.out.println("Player " + o.getName() + " hand:");
                    // for (Card c : o.getHand()) {
                    // System.out.println(c);
                    // }
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

            System.out.println("Round over");

            return true;
        }
        return false;
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
