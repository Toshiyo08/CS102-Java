package Players;
// package CS102cardgame.src;

import java.util.ArrayList;

import Base.*;

public class Player {
    private String type; // Player or Bot
    private String name;
    private boolean isPlaying; //active Player or not
    private boolean isChecked; //checked/called or not
    private boolean isBigBlind; //Big Blind or not
    private boolean isSmallBlind; //Small Blind or not
    private boolean isBlindPaid; //Check if the player has paid the blind
    private int betAmt;
    private int balance;
    private ArrayList<Card> hand; 
    
    //what is type?
    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.isPlaying = true;
        this.isChecked = false;
        this.hand = new ArrayList<Card>();
        this.balance = 300;
        this.betAmt = 0;
        this.isBigBlind = false;
        this.isSmallBlind = false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsChecked () {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int getBet(){
        return this.betAmt;
    }

    public void setBet(int newBet){
        this.betAmt = newBet;
    }

    public void raiseBet(int raisedAmt) {
        this.betAmt += raisedAmt;
        this.balance -= raisedAmt;
    }

    public int getBalance(){
        return this.balance;
    }

    //Increment balance by amount you win
    public void setEndBalance(int raised) {
        this.balance += raised;
    }

    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) this.hand;
    }
    
    public void setIsBigBlind (boolean status) {
        this.isBigBlind = status;
    }
    
    public boolean getIsBigBlind() {
        return this.isBigBlind;
    }

    public void setIsSmallBlind (boolean status) {
        this.isSmallBlind = status;
    }
    
    public boolean getIsSmallBlind () {
        return this.isSmallBlind;
    }

    public void setIsBlindPaid(boolean status) {
        this.isBlindPaid = status;
    }

    public boolean getIsBlindPaid() {
        return this.isBlindPaid;
    }

    // Misc Actions: Draw, Buy in
    public void draw(Card newCard) {
        this.hand.add(newCard);
    }   

}
