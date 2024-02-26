package CS102cardgame.src;
import java.util.*;

public class Game {
    private int numTurns;
    private Boolean bettingRoundComplete;

    public static void main(String[] args) {
        // In 1 Game, multiple rounds:
        // In 1 round: 


        // plyr balance outside
        // Initialise player

        while (Game) {
            // Prompt for new game
            while (round) {
                // stuff
            }
        }


        // Initialise Players
        Player plyr = new Player("Tom", "Player");
        //create deck---------------------------------------------------------------------------
        Deck deck1 = new Deck();
        Table table1 = new Table();
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
            Table.drawComm(deck1.dealCard);
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
