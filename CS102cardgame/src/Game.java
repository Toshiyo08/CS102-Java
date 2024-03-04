//package CS102cardgame.src;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private int numTurns;
    private Boolean bettingRoundComplete;

    //game constructor
    public Game() {
        this.numTurns = 0;
        this.bettingRoundComplete = false;
    }

    public static void main(String[] args) {
        // In 1 Game, multiple rounds:

        // Initiliase an Arraylist to store number of players
        ArrayList<Player> playersList = new ArrayList<Player>();

        int numberOfPlayers = 0;

        Scanner playerNumbers = new Scanner(System.in);
        // Keeps prompting for number of players to start game
        while(true) {
            try {
                System.out.println("Enter number of players: ");
                playerNumbers = new Scanner(System.in);
                numberOfPlayers = playerNumbers.nextInt();
                break;
            } catch (InputMismatchException ime){
                System.out.println("Please enter valid number");
            }
            finally {
                playerNumbers.nextLine();
            }
        }
        playerNumbers.close();
        
        // Initialise a player that user controls and add into list of players
        Player userPlayer = new Player("Tom", "Player");
        playersList.add(userPlayer);
        

        // Initialise number of bots and add them into list of players
        for (int i = 0; i < numberOfPlayers - 1; i++){
            playersList.add(new PlayerBot("Bot", "Bot"));
        }

        // initialise balance of chips for all players outside of game loop

        // In 1 round: =============================================================================================
        Table table1 = new Table();
        for (Player player: playersList){
            table1.addNewPlayer(player);
        }
        //create deck---------------------------------------------------------------------------
        Deck deck1 = new Deck();
        
        //Card cards = new Card(null, 0);


        // Shuffle Deck---------------------------------------------------------------------------
        deck1.shuffleDeck();

        // Cut Deck---------------------------------------------------------------------------
        Scanner getCutNum = new Scanner (System.in);
        String 
        deck1.cutDeck();

        //deal cards---------------------------------------------------------------------------
        int numTurns;
        for (Player e: table1.getActivePlayers()) {
            numTurns++;
            plyr.draw(deck1.dealCard());
            plyr.draw(deck1.dealCard());
        }

        // Burn Cards---------------------------------------------------------------------------
        deck1.burnCard();

        // 1st Betting Round---------------------------------------------------------------------------
        
        int numChecks = 0;
        Scanner plyrturn = new Scanner(System.in);
        String plyrturninput = null;
        Boolean bettingRoundComplete = false;

        
        while (!bettingRoundComplete) {
            // Player action: 
                // Check: If player is ok with price
                // Call: If player is ok with NEW price
                // Raise: If player wants to raise bet
                // Fold: If player wants to not play for that round anymore
                for (Player o : table1.getActivePlayers()) {
                    
                    isPlayerTurnComplete(o, table1.getCurrentBetAmt(), table1);
                    if (isCheckedCalled(o, table1)){ // Checks if player current bet matches table's current bet
                        numChecks++;
                    } else {
                        numChecks = 0;
                    }
                }

                if (numChecks == table1.getActivePlayers().size()) {
                    bettingRoundComplete = true;
                }

            

            // Update numChecks:
                // If Check/Call:
                // Increase numChecks by 1
                // If Raise:
                // Reset numChecks to 0
                // If fold:
                // Decrease numTurns
            // Check if bettingRoundComplete, 
                // If not:
                // Move on to next player
                // If yes:
                // End 1st Betting Round
                // getWinCheck()
        }

        // Check if win condition fulfilled:
            // If everyone but 1 person fold, move to openHand()
            // BUT as long as more than 1 person remains, openHand() can only be called AFTER river(last card) has been revealed
                // Procede to openHand() IF AND ONLY IF river has been revealed
        // getWinCheck()


        // Deal Flop (3 cards)---------------------------------------------------------------------------
        for (int i = 0; i < 3; i++) {
            Table.drawComm(deck1.dealCard());
        }

        //2nd Betting Round---------------------------------------------------------------------------
        while (!bettingRoundComplete) {
                for (Player o : table1.getActivePlayers()) {
                    
                    isPlayerTurnComplete(o, table1.getCurrentBetAmt(), table1); //!! PRINT the bot's actions
                    if (isCheckedCalled(o, table1)){ // Checks if player current bet matches table's current bet
                        numChecks++;
                    } else {
                        numChecks = 0;
                    }
                }

                if (numChecks == table1.getActivePlayers().size()) {
                    bettingRoundComplete = true;
                }
        }

        // Deal Turn---------------------------------------------------------------------------
        deck1.burnCard();
        table1.drawComm(Deck.dealCard);

        // 3rd Betting Round---------------------------------------------------------------------------
        

        // Deal River---------------------------------------------------------------------------
        deck1.burnCard();
        table1.drawComm(Deck.dealCard);

        // Last Betting Round---------------------------------------------------------------------------
        //openHand()

        // Assign Pot---------------------------------------------------------------------------
        

    }
    
}
