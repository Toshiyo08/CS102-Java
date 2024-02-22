package CS102cardgame.src;

public class Game {
    private int numTurns;
    private Boolean bettingRoundComplete;
    // In 1 Game, multiple rounds:
    // In 1 round: 
    //create deck---------------------------------------------------------------------------
    Deck deck1 = new Deck();
    Table table1 = new Table();
    // Shuffle Deck---------------------------------------------------------------------------
    shuffleDeck(deck1);

    // Cut Deck---------------------------------------------------------------------------
    deck1.cutDeck();

    //deal cards---------------------------------------------------------------------------
    for (Player plyr: TablePLayer) {
        numTurns++;
        plyr.draw(deck1.dealCard);
        plyr.draw(deck1.dealCard);
    }

    // Burn Cards---------------------------------------------------------------------------
    deck1.burnCard();

    // 1st Betting Round---------------------------------------------------------------------------
    
    int numChecks = 0;
    while (!bettingRoundComplete) {
        // Player action: 
            // Check: If player is ok with price
            // Call: If player is ok with NEW price
            // Raise: If player wants to raise bet
            // Fold: If player wants to not play for that round anymore
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

        if (numChecks == numTurns) {
            bettingRoundComplete = true;
        }
    }

    // Check if win condition fulfilled:
        // If everyone but 1 person fold
        // If everyone but 2 people fold, and the 2 people check and call each other
        // IN ESSENCE as long as more than 1 person remains, openHand() can only be called AFTER river has been revealed
            // Procede to openHand() IF AND ONLY IF river has been revealed
    // getWinCheck()


    // Deal Flop (3 cards)---------------------------------------------------------------------------
    for (int i = 0; i < 3; i++) {
        Table.drawComm(deck1.dealCard);
    }

    //2nd Betting Round---------------------------------------------------------------------------
    

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
