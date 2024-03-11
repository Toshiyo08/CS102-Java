
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
        //     try {
        //         System.out.println("Enter number of players: ");
        //         numberOfPlayers = playerNumbers.nextInt();
        //         break;
        //     } catch (InputMismatchException ime) {
        //         System.out.println("Please enter valid number");
        //     } finally {
        //         playerNumbers.nextLine();
        //     }
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
        //     playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        // initialise balance of chips for all players outside of game loop

        // In 1 round:
        // =============================================================================================
        Table table1 = new Table();
        // create
        // deck---------------------------------------------------------------------------
        Deck deck1 = new Deck();

        // Card cards = new Card(null, 0);
        Game game = new Game();

        // Shuffle
        // Deck---------------------------------------------------------------------------
        Collections.shuffle(deck1.getCards());

        // Cut
        // Deck---------------------------------------------------------------------------
        // Scanner getCutNum = new Scanner (System.in);
        // deck1.cutDeck();

        // deal
        // cards---------------------------------------------------------------------------
        for (Player e : playersList) {
            e.draw(deck1.dealCard());
            e.draw(deck1.dealCard());
        }

        // Burn
        // Cards---------------------------------------------------------------------------
        deck1.burnCard();

        // 1st Betting

        bettingRound(playersList, table1); // DONT DELETE THIS LINE
        

        // Round---------------------------------------------------------------------------

        // Deal Flop (3
        // cards)---------------------------------------------------------------------------
        for (int i = 0; i < 3; i++) {
            table1.drawComm(deck1.dealCard());
        }

        for (Card c : table1.getCommCards()) {
            System.out.println(c);
        }

        userPlayer.setChecked(false);
        userPlayer1.setChecked(false);
        userPlayer2.setChecked(false);
        userPlayer.setBet(0);
        table1.setCurrentBet(0);

        // 2nd Betting
        // Round---------------------------------------------------------------------------
       
        bettingRound(playersList, table1);

        // Deal
        // Turn---------------------------------------------------------------------------
        deck1.burnCard();
        table1.drawComm(deck1.dealCard());

        for (Card c : table1.getCommCards()) {
            System.out.println(c);
        }
        
        userPlayer.setChecked(false);
        userPlayer1.setChecked(false);
        userPlayer2.setChecked(false);
        // 3rd Betting
        // Round---------------------------------------------------------------------------

        bettingRound(playersList, table1);

        // Deal
        // River---------------------------------------------------------------------------
        deck1.burnCard();
        table1.drawComm(deck1.dealCard());

        for (Card c : table1.getCommCards()) {
            System.out.println(c);
        }

        userPlayer.setChecked(false);
        userPlayer1.setChecked(false);
        userPlayer2.setChecked(false);

        bettingRound(playersList, table1);

        for (Player o : playersList){
            System.out.println("Player " + o.getName() + " hand:");
            for(Card c : o.getHand()){
                System.out.println(c);
                System.out.println();
            }
        }

        // Last Betting
        // Round---------------------------------------------------------------------------
        System.out.println("game over");

    }

    public static void bettingRound(ArrayList<Player> playersList, Table table1) {
        ArrayList<Player> current = new ArrayList<Player>();
        for (Player o : playersList) {
            if (o.getIsPlaying() == true) {
                current.add(o);
            }
        }
        // BIG BLIND SMALL BLIND SORTING ORDER

        int counter = 0;
        Boolean betted = false;
        while (counter != getCurrentSize(current)) {
            for (Player o : current) {

                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {

                    Scanner sc = new Scanner(System.in);
                    if (o.getBet() < table1.getCurrentBetAmt()) {
                        System.out.println("1: Call, 2: Raise, 3: Fold");
                    } else {
                        System.out.println("1: Check, 2: Bet, 3: Fold");
                    }
                    System.out.println("Action Num> ");
                    int action = sc.nextInt();

                    if (action == 1) {
                        o.setChecked(true);
                        // System.out.println("Before Bet " + o.getBet());
                        // System.out.println("Before Balance " + o.getBalance());
                        // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());
                        o.setBet(table1.getCurrentBetAmt());
                        o.setBalance(o.getBet());
                        counter++;
                        // System.out.println("After Bet " + o.getBet());
                        // System.out.println("After Balance " + o.getBalance());
                        // System.out.println("After Table Bet " + table1.getCurrentBetAmt());

                    } else if (action == 2) {
                        System.out.println("Bet?> ");
                        int newBet = sc.nextInt();
                        // System.out.println("Before Bet " + o.getBet());
                        // System.out.println("Before Balance " + o.getBalance());
                        // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());
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
                        // System.out.println("After Bet " + o.getBet());
                        // System.out.println("After Balance " + o.getBalance());
                        // System.out.println("After Table Bet " + table1.getCurrentBetAmt());

                    } else if (action == 3) {
                        o.setIsPlaying(false);
                        // counter++;
                        // current.remove(o);

                    }
                } else if (o.getType().equals("Bot") && o.getChecked() == false && o.getIsPlaying() == true){
                    // botLogic(o); // void method, returns nothing
                    // promtBotLogic(o) -> checks their hand+commCard points -> returns action int

                        Random random = new Random();
                        int n = random.nextInt(3);
    
                        if (n == 1) {
                            System.out.println("Bot " + o.getName() + " has check");
                            o.setChecked(true);
                            // System.out.println("Before Bet " + o.getBet());
                            // System.out.println("Before Balance " + o.getBalance());
                            // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            // System.out.println("After Bet " + o.getBet());
                            // System.out.println("After Balance " + o.getBalance());
                            // System.out.println("After Table Bet " + table1.getCurrentBetAmt());
    
                        } else if (n == 2) {
                            System.out.println("Bot " + o.getName() + " has bet");
                            int newBet = 5;
                            // System.out.println("Before Bet " + o.getBet());
                            // System.out.println("Before Balance " + o.getBalance());
                            // System.out.println("Before Table Bet " + table1.getCurrentBetAmt());
                            o.setBet(newBet);
                            o.setBalance(newBet);
                            table1.setCurrentBet(newBet);
                            System.out.println(table1.getCurrentBetAmt());
                            o.setChecked(true);
                            counter = 1;
                            for (Player e : current) {
                                if (!e.equals(o)) {
                                    e.setChecked(false);
                                }
                            }
                            // System.out.println("After Bet " + o.getBet());
                            // System.out.println("After Balance " + o.getBalance());
                            // System.out.println("After Table Bet " + table1.getCurrentBetAmt());
    
                        } else if (n == 3) {
                            System.out.println("Bot " + o.getName() + " has bet");
                            o.setIsPlaying(false);
                            // counter++;
                            // current.remove(o);
    
                        }

                

            }

        }
    }
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
