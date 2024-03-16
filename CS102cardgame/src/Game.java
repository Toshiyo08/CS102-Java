
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
        for (Player p : playersList) {
            System.out.println(p.getBalance());
        }
        // Burn
        // Cards---------------------------------------------------------------------------
        deck1.burnCard();

        // 1st Betting
        boolean afterRound1 = false;
        bettingRound(playersList, table1, afterRound1); // DONT DELETE THIS LINE

        for (Player p : playersList) {
            System.out.println(p.getBalance());
        }
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
        afterRound1 = true;

        // 2nd Betting
        // Round---------------------------------------------------------------------------

        bettingRound(playersList, table1, afterRound1);
        for (Player p : playersList) {
            System.out.println(p.getBalance());
        }
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
        userPlayer.setBet(0);
        table1.setCurrentBet(0);
        // 3rd Betting
        // Round---------------------------------------------------------------------------

        bettingRound(playersList, table1, afterRound1);
        for (Player p : playersList) {
            System.out.println(p.getBalance());
        }
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
        userPlayer.setBet(0);
        table1.setCurrentBet(0);

        bettingRound(playersList, table1, afterRound1);
        Map<String,Integer> winner = new HashMap<>();

        for (Player o : playersList) {
            System.out.println("Player " + o.getName() + " hand:");
            for (Card c : o.getHand()) {
                System.out.println(c);
            }
            winner.put(o.getName(),Hand2.getHandValue(o.getHand(), table1.getCommCards()));
            System.out.println();
        }
        int highest = 0;

        for (String name: winner.keySet()){
            if (winner.get(name) > highest){
                highest = winner.get(name);
            }
        }

        ArrayList<String> winnerList = new ArrayList<String>();
        System.out.println(highest);
        for (String name: winner.keySet()){
            if (winner.get(name) == highest){
            winnerList.add(name);
            }
        }

        for (String s : winnerList){
            System.out.println("Winner is " + s);
        }

        // Last Betting
        // Round---------------------------------------------------------------------------
        System.out.println("game over");

        for (Player p : playersList) {
            System.out.println(p.getBalance());
        }

    }

    public static String getInput(){
        while(true){
            try {
                Scanner sc = new Scanner(System.in);
                String input = sc.nextLine();
                input = input.toLowerCase();
                if (input.contains("check") || input.contains("fold") || input.contains("call")){
                    return input;
                } else if (input.contains("bet")){
                    System.out.println("Bet?> ");
                    return input;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e){
                System.out.println("try again");
            }
        }
    }

    public static int getBetInput(Player p){
        int input;
        while (true){
            try {
                Scanner sc = new Scanner(System.in);
                if (sc.hasNextInt()){
                    input = sc.nextInt();
                    if (input > p.getBalance()){
                        throw new InputMismatchException("Insufficient Balance, Enter new bet");
                    }
                    return input;
                } else {
                    throw new InputMismatchException("Invalid input");
                }
            } catch (InputMismatchException e){
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
        boolean hasChecked = false;
        String previousAction = null;

        while (counter != getCurrentSize(current)) {
            for (Player o : current) {

                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {
                    String action = null;
                    

                        if (o.getBet() < table1.getCurrentBetAmt()) {
                            System.out.println("Call / Raise / Fold");
                            System.out.println("Action> ");
                            action = getInput();
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Call";
                        } else {
                            System.out.println("Check / Bet / Fold");
                            System.out.println("Action> ");
                            action = getInput();
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Check";
                        }
                        if (action.equals("bet")) {
                            Scanner sc = new Scanner(System.in);
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
    
                        } if (action.equals("fold")) {
                            o.setIsPlaying(false);
                            // counter++;
                            // current.remove(o);
    
                        }
                } else if (o.getType().equals("Bot") && o.getChecked() == false && o.getIsPlaying() == true) {
                    // botLogic(o); // void method, returns nothing
                    // promtBotLogic(o) -> checks their hand+commCard points -> returns action int
                    int botAction = 1;
                    if (afterRound1){
                        if ((Hand2.getHandValue(o.getHand(), table1.getCommCards()))  <= 28 && (Hand2.getHandValue(o.getHand(), table1.getCommCards()) > 0)){
                                if (previousAction.equals("Check")){
                                    botAction = 1;
                                } 
                        } else {
                            if (previousAction.equals("Check")){
                                botAction = 2;
                            } else {
                                botAction = 1;
                            }
                            
                        } 
                    }

                    if (botAction == 1) {
                        if (o.getBet() < table1.getCurrentBetAmt()){
                            System.out.println("Bot " + o.getName() + " has called");
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Call";
                        } else {
                            System.out.println("Bot " + o.getName() + " has checked");
                            o.setChecked(true);
                            o.setBet(table1.getCurrentBetAmt());
                            o.setBalance(o.getBet());
                            counter++;
                            previousAction = "Check";
                        }
                        


                    } else if (botAction == 2) {
                        System.out.println("Bot " + o.getName() + " has bet");
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
                        System.out.println("Bot " + o.getName() + " has bet");
                        o.setIsPlaying(false);
                        // counter++;
                        // current.remove(o);

                    }

                }
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
