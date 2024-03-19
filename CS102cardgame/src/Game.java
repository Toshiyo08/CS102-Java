
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

        // Initialise number of bots and add them into list of players
        // for (int i = 0; i < 3; i++) {
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        boolean gameContinue = true;
        int allWinning = userPlayer.getBalance() * playersList.size();

        System.out.println("                                                                       Welcome to");
        System.out.println("                                                        .------..------..------..------..------.          ");
        System.out.println("                                                        |T.--. ||E.--. ||X.--. ||A.--. ||S.--. |          ");
        System.out.println("                                                        | :/\\: || (\\/) || :/\\: || (\\/) || :/\\: |          ");
        System.out.println("                                                        | (__) || :\\/: || (__) || :\\/: || :\\/: |          ");
        System.out.println("                                                        | '--'T|| '--'E|| '--'X|| '--'A|| '--'S|          ");
        System.out.println("                                                        `------'`------'`------'`------'`------'          ");
        System.out.println("                                                .------..------..------..------..------..------..------.");
        System.out.println("                                                |H.--. ||O.--. ||L.--. ||D.--. ||'.--. ||E.--. ||M.--. |");
        System.out.println("                                                | :/\\: || :/\\: || :/\\: || :/\\: || :/\\: || (\\/) || (\\/) |");
        System.out.println("                                                | (__) || :\\/: || (__) || (__) || :\\/: || :\\/: || :\\/: |");
        System.out.println("                                                | '--'H|| '--'O|| '--'L|| '--'D|| '--''|| '--'E|| '--'M|");
        System.out.println("                                                `------'`------'`------'`------'`------'`------'`------'");
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
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
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
        System.out.print("100%");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println();




        while (gameContinue) {
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
            // for (Player p : playersList) {
            // System.out.println(p.getBalance());
            // }
            deck1.burnCard();

            boolean afterRound1 = false;
            // 1st Betting Round------------------------------
            bettingRound(playersList, table1, afterRound1); // DONT DELETE THIS LINE
            System.out.println(table1.getPot());
            for (Player p : playersList) {
                System.out.println(p.getBalance());
            }

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
            System.out.println(table1.getPot());
            for (Player p : playersList) {
                System.out.println(p.getBalance());
            }
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
            System.out.println(table1.getPot());
            for (Player p : playersList) {
                System.out.println(p.getBalance());
            }
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
                }
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
        System.out.println(userPlayer.getBalance());

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

                    if (o.getBet() < table1.getCurrentBetAmt()) {
                        System.out.println("Call / Raise / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        o.setBet(table1.getCurrentBetAmt());
                        o.setBalance(o.getBet());
                        counter++;
                        previousAction = "Call";
                    } else {
                        System.out.println("Check / Bet / Fold");
                        System.out.print("Action> ");
                        action = getInput();
                        o.setChecked(true);
                        o.setBet(table1.getCurrentBetAmt());
                        o.setBalance(o.getBet());
                        counter++;
                        previousAction = "Check";
                    }
                    if (action.equals("bet")) {

                        int newBet = getBetInput(o);
                        o.setBet(newBet);
                        o.setBalance(newBet);
                        table1.setCurrentBet(newBet);
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
                    // botLogic(o); // void method, returns nothing
                    // promtBotLogic(o) -> checks their hand+commCard points -> returns action int
                    int botAction = PlayerBot.getBotAction(o, previousAction, afterRound1, table1);
                    // int botAction = 1;
                    // if (afterRound1) {// fold if raise too high for given hand value
                    // if ((Hand2.getHandValue(o.getHand(), table1.getCommCards())) <= 28
                    // && (Hand2.getHandValue(o.getHand(), table1.getCommCards()) > 0)) {
                    // if (previousAction == null) {
                    // previousAction = "Check";
                    // }
                    // if (previousAction.equals("Check") || previousAction.equals("Fold")) {

                    // botAction = 1;
                    // } else {
                    // botAction = 3;
                    // System.out.println("Bot " + o.getName() + " has folded");
                    // }
                    // } else {
                    // if (previousAction == null) {
                    // previousAction = "Check";
                    // }
                    // if (previousAction.equals("Check") || previousAction.equals("Fold")) {
                    // botAction = 2;
                    // } else {
                    // botAction = 1;
                    // }
                    // }
                    // }

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()) {
                            System.out.println(o.getName() + ": Call");
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Call";
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
                                Random random = new Random();
                                int randomreply = random.nextInt(5) + 1;
                                PlayerBot.randomangryreply(f.getName(), o.getName(), randomreply);
                            }
                        }

                        int newBet = 5;
                        // System.out.println("Before Bet " + o.getBet());
                        // System.out.println("Before Balance " + o.getBalance());
                        // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());
                        o.setBet(newBet);
                        o.setBalance(newBet);
                        table1.setCurrentBet(newBet);
                        System.out.println("The current table bet amount is " + table1.getCurrentBetAmt());
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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                table1.setPot(o.getBet());
                o.setBet(0);

            }

        }
        for (Player o : playersList) {
            o.setChecked(false);
            if (o.getType().equals("Player")) {
                o.setBet(0);
            }

        }
        table1.setCurrentBet(0);
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

}
