package CS102cardgame.src;

import java.util.*;

public class Table {
    private ArrayList<Card> commCards;
    private int pot;
    private ArrayList<Player> activePlayers;
    private int currentBetAmt;

    public Table() {
        this.commCards = null;
        this.pot = 0;
    }

    public void addNewPlayer(Player plyr) {
        activePlayers.add(plyr);
    }

    public ArrayList<Player> getActivePlayers() {
        return activePlayers;
    }

    public void raiseCurrentBetAmt (int raisedValue) {
        this.currentBetAmt = raisedValue;
    }

    public int getCurrentBetAmt () {
        return currentBetAmt;
    }


    public void drawComm(Card newCard) {
        this.commCards.add(newCard);
    }

    public int getPot(ArrayList<Player> Tableplayer) {
        pot = 0;
        for (Player plyr: Tableplayer) {
            pot += plyr.getBet();
        }
        return pot;
    }

    public int checkPot() {
        return pot;
    }
    
}
