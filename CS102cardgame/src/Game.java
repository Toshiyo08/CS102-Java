
//package CS102cardgame.src;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Game extends JPanel {

    /** The maximum number of community cards. */
    private static final int NO_OF_CARDS = 5;

    /** Labels with the community cards. */
    private static final JLabel[] cardLabels;

    // private int numTurns;
    // private Boolean bettingRoundComplete;

    // game constructor
    // public Game() {
    // this.numTurns = 0;
    // this.bettingRoundComplete = false;
    // }

    public static void main(String[] args) {
        // In 1 Game, multiple rounds:

        // Initiliase an Arraylist to store number of players
        // ArrayList<Player> playersList = new ArrayList<Player>();

        // int numberOfPlayers = 0;

        // Scanner playerNumbers = new Scanner(System.in);
        // // Keeps prompting for number of players to start game
        // while(true) {
        // try {
        // System.out.println("Enter number of players: ");
        // playerNumbers = new Scanner(System.in);
        // numberOfPlayers = playerNumbers.nextInt();
        // break;
        // } catch (InputMismatchException ime){
        // System.out.println("Please enter valid number");
        // }
        // finally {
        // playerNumbers.nextLine();
        // }
        // }
        // playerNumbers.close();

        // // Initialise a player that user controls and add into list of players
        // Player userPlayer = new Player("Tom", "Player");
        // playersList.add(userPlayer);

        // // Initialise number of bots and add them into list of players
        // for (int i = 0; i < numberOfPlayers - 1; i++){
        // playersList.add(new PlayerBot("Bot", "Bot"));
        // }

        // // initialise balance of chips for all players outside of game loop

        // // In 1 round:
        // =============================================================================================
        // Table table1 = new Table();
        // for (Player player: playersList){
        // table1.addNewPlayer(player);
        // }
        // //create
        // deck---------------------------------------------------------------------------
        // Deck deck1 = new Deck();

        // //Card cards = new Card(null, 0);

        // //game constructor
        // public Game() {
        // this.numTurns = 0;
        // this.bettingRoundComplete = false;
        // }

        // // Shuffle
        // Deck---------------------------------------------------------------------------
        // deck1.shuffleDeck();

        // // Cut
        // Deck---------------------------------------------------------------------------
        // Scanner getCutNum = new Scanner (System.in);
        // String
        // deck1.cutDeck();

        // //deal
        // cards---------------------------------------------------------------------------
        // int numTurns;
        // for (Player e: table1.getActivePlayers()) {
        // numTurns++;
        // plyr.draw(deck1.dealCard());
        // plyr.draw(deck1.dealCard());
        // }

        // // Burn
        // Cards---------------------------------------------------------------------------
        // deck1.burnCard();

        // // 1st Betting

        // bettingRound(totalPlayers, table1); DONT DELETE THIS LINE

        // Round---------------------------------------------------------------------------

        // int numChecks = 0;
        // Scanner plyrturn = new Scanner(System.in);
        // String plyrturninput = null;
        // Boolean bettingRoundComplete = false;

        // while (!bettingRoundComplete) {
        // // Player action:
        // // Check: If player is ok with price
        // // Call: If player is ok with NEW price
        // // Raise: If player wants to raise bet
        // // Fold: If player wants to not play for that round anymore
        // for (Player o : table1.getActivePlayers()) {

        // isPlayerTurnComplete(o, table1.getCurrentBetAmt(), table1); // Replace with
        // if player: scan, if bot: logic (logic returns)
        // if (isCheckedCalled(o, table1)){ // Checks if player current bet matches
        // table's current bet
        // numChecks++;
        // } else {
        // numChecks = 0;
        // }
        // }

        // if (numChecks == table1.getActivePlayers().size()) {
        // bettingRoundComplete = true;
        // }

        // // Update numChecks:
        // // If Check/Call:
        // // Increase numChecks by 1
        // // If Raise:
        // // Reset numChecks to 0
        // // If fold:
        // // Decrease numTurns
        // // Check if bettingRoundComplete,
        // // If not:
        // // Move on to next player
        // // If yes:
        // // End 1st Betting Round
        // // getWinCheck()
        // }

        // Check if win condition fulfilled:
        // If everyone but 1 person fold, move to openHand()
        // BUT as long as more than 1 person remains, openHand() can only be called
        // AFTER river(last card) has been revealed
        // Procede to openHand() IF AND ONLY IF river has been revealed
        // getWinCheck()

        // Deal Flop (3
        // cards)---------------------------------------------------------------------------
        // for (int i = 0; i < 3; i++) {
        // Table.drawComm(deck1.dealCard());
        // }

        // 2nd Betting
        // Round---------------------------------------------------------------------------
        // while (!bettingRoundComplete) {
        // for (Player o : table1.getActivePlayers()) {

        // isPlayerTurnComplete(o, table1.getCurrentBetAmt(), table1); //!! PRINT the
        // bot's actions
        // if (isCheckedCalled(o, table1)){ // Checks if player current bet matches
        // table's current bet
        // numChecks++;
        // } else {
        // numChecks = 0;
        // }
        // }

        // if (numChecks == table1.getActivePlayers().size()) {
        // bettingRoundComplete = true;
        // }
        // }

        // Deal
        // Turn---------------------------------------------------------------------------
        // deck1.burnCard();
        // table1.drawComm(Deck.dealCard);

        // 3rd Betting
        // Round---------------------------------------------------------------------------

        // Deal
        // River---------------------------------------------------------------------------
        // deck1.burnCard();
        // table1.drawComm(Deck.dealCard);

        // Last Betting
        // Round---------------------------------------------------------------------------
        // openHand()

        // Assign
        // Pot---------------------------------------------------------------------------

        // ImageIcon card = new ImageIcon("C:/CS102-Java/CS102cardgame/cards/2c.gif");

        // GridBagConstraints gc = new GridBagConstraints();

        // // 5 card positions
        // cardLabels = new JLabel[NO_OF_CARDS];
        // for (int i = 0; i < 5; i++) {
        //     cardLabels[i] = new JLabel(ImageFinder.getIcon("/images/card_placeholder.png"));
        //     gc.gridx = i;
        //     gc.gridy = 2;
        //     gc.gridwidth = 1;
        //     gc.gridheight = 1;
        //     gc.anchor = GridBagConstraints.CENTER;
        //     gc.fill = GridBagConstraints.NONE;
        //     gc.weightx = 0.0;
        //     gc.weighty = 0.0;
        //     gc.insets = new Insets(5, 1, 5, 1);
        //     add(cardLabels[i], gc);
        // }

        // JLabel label = new JLabel();
        // label.setText("Texas Hold Em");
        // // label.setIcon(card);

        // JFrame frame = new JFrame("Testing");
        // frame.setVisible(true);
        // frame.setSize(600, 600);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLayout(new GridBagLayout());

        // frame.add(label);

        // JPanel panel = new JPanel();
        // panel.setLayout(new BorderLayout());
        // panel.setBackground(new Color(53, 101, 77));

        // frame.add(panel);

    }

    public static void bettingRound(ArrayList<Player> totalPlayers, Table table1) {
        ArrayList<Player> current = new ArrayList<Player>();
        for (Player o : totalPlayers) {
            if (o.getIsPlaying() == true) {
                current.add(o);
            }
        }

        //BIG BLIND SMALL BLIND SORTING ORDER

        int counter = 0;
        Boolean betted = false;
        while (counter != getCurrentSize(current)) {
            
            for (Player o : current) {

                if (o.getType().equals("Player") && o.getChecked() == false && o.getIsPlaying() == true) {
                    
                    Scanner sc = new Scanner(System.in);
                    if (o.getBet() < table1.getCurrentBet()) {
                        System.out.println("1: Call, 2: Raise, 3: Fold");
                    } else {
                        System.out.println("1: Check, 2: Bet, 3: Fold");
                    }
                    System.out.println("Action Num> ");
                    int action = sc.nextInt();

                    if (action == 1) {
                        o.setChecked(true);
                        System.out.println("Before Bet " + o.getBet());
                        System.out.println("Before Balance "+ o.getBalance());
                        System.out.println("Before Table Bet " + table1.getCurrentBet());
                        o.setBet(table1.getCurrentBet());
                        o.setBalance(o.getBet());
                        counter++;
                        System.out.println("After Bet " + o.getBet());
                        System.out.println("After Balance "+ o.getBalance());
                        System.out.println("After Table Bet " + table1.getCurrentBet());

                    } else if (action == 2) {
                        System.out.println("Bet?> ");
                        int newBet = sc.nextInt();
                        System.out.println("Before Bet " + o.getBet());
                        System.out.println("Before Balance "+ o.getBalance());
                        System.out.println("Before Table Bet " + table1.getCurrentBet());
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
                        System.out.println("After Bet " + o.getBet());
                        System.out.println("After Balance "+ o.getBalance());
                        System.out.println("After Table Bet " + table1.getCurrentBet());
                        

                    } else if (action == 3) {
                        o.setIsPlaying(false);
                        // counter++;
                        // current.remove(o);
                        
                    }
                } else {
                    // botLogic(o); // void method, returns nothing
                    
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
