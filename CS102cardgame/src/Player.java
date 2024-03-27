// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String type; // Player or Bot
    private String name;
    private boolean isPlaying;
    private boolean checked;
    private boolean bigBlind;
    private boolean smallBlind;
    private boolean blinded;
    private int betAmt;
    private int balance;
    private int finalScore = 0;
    private ArrayList<Card> hand; // 10jqka|23456789 10|11|12|13|14
    
    //what is type?
    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.isPlaying = true;
        this.checked = false;
        // this.status = true/false -> playing/folded
        // this.check = true/false -> check/called already
        this.hand = new ArrayList<Card>();
        this.balance = 300;
        this.betAmt = 0;
        this.bigBlind = false;
        this.smallBlind = false;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public Boolean getChecked () {
        return checked;
    }

    public void setChecked(Boolean isCheck) {
        this.checked = isCheck;
    }

    public Boolean getIsPlaying() {
        return this.isPlaying;
    }

    public void setIsPlaying(Boolean isPlaying) {
        this.isPlaying = isPlaying;
    }

    public int getBet(){
        return this.betAmt;
    }

    public void setBet(int newBet){
        this.betAmt = newBet;
    }

    public void raiseBet(int raisedamt) {
        this.betAmt += raisedamt;
        this.balance -= raisedamt;
    }

    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int raised) {
        this.balance -= raised;
    }

    public void setEndBalance(int raised) {
        this.balance += raised;
    }

    public ArrayList<Card> getHand(){
        return (ArrayList<Card>) this.hand;
    }

    public void setBigBlind (Boolean status) {
        this.bigBlind = status;
    }
    
    public Boolean getBigBlind() {
        return this.bigBlind;
    }
    public void setSmallgBlind (Boolean status) {
        this.smallBlind = status;
    }
    
    public Boolean getSmallBlind () {
        return this.smallBlind;
    }

    public void setBlinded(Boolean status) {
        this.blinded = status;
    }

    public Boolean getBlinded() {
        return this.blinded;
    }
    
    
    
    // Misc Actions: Draw, Buy in
    public void draw(Card newCard) {
        this.hand.add(newCard);
    }

    // Game Actions: Fold, Call, Raise, Check
    public void fold() {
        this.hand = null;
        //pplayerchips will still remain
        //playerChips = 0;
        this.isPlaying = false;
    }

    public void setCall(int calledAmt) {
        this.betAmt = calledAmt;
        this.balance -= calledAmt;
    }

    public void setRaise(int raisedAmt) {
        this.betAmt = raisedAmt;
        this.balance -= raisedAmt;
    }

    public void setFinalScore(int finalScore){
        this.finalScore = finalScore;
    }

    public int getFinalScore(){
        return finalScore;
    }

   

}
