package Base;

import java.util.*;

public class Table {
    private ArrayList<Card> commCards;
    private int pot;
    private int currentBetAmt;

    public Table() {
        this.commCards = new ArrayList<Card>();
        this.pot = 0;
        this.currentBetAmt = 0;
    }

    public ArrayList<Card> getCommCards() {
        return commCards;
    }
    
    public void setCurrentBetAmt (int raisedValue) {
        this.currentBetAmt = raisedValue;
    }

    public void raiseCurrentBetAmt (int raisedAmt) {
        this.currentBetAmt += raisedAmt;
    }

    public int getCurrentBetAmt () {
        return currentBetAmt;
    }

    public void drawComm(Card newCard) {
        this.commCards.add(newCard);
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int amount) {
        pot += amount;
    }

    public void raisePot(int raisedAmt) {
        this.pot += raisedAmt;
    }
    
}
