// package CS102cardgame.src;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String type;
    private String name;
    private boolean playing;
    private boolean check;
    private boolean bigBlind;
    private boolean smallBlind;
    private int betAmt;
    private int playerChips;
    private ArrayList<Card> hand; // 10jqka|23456789 10|11|12|13|14
    
    public Player(String name, String type) {
        this.name = name;
        this.type = type;
        this.playing = true;
        this.hand = new ArrayList<Card>();
        this.playerChips = 100;
        this.betAmt = 0;
        this.bigBlind = false;
        this.smallBlind = false;
    }

    public Boolean getCheckStatus () {
        return check;
    }

    public int getBet(){
        return this.betAmt;
    }

    public int getChips(){
        return this.playerChips;
    }
    
    public Boolean setCheckTrue(){
        return this.check = true;
    }

    public Boolean setCheckfalse(){
        return this.check = false;
    }
    
    // Misc Actions: Draw, Buy in
    public void draw(Card newCard) {
        this.hand.add(newCard);
    }

    // Game Actions: Fold, Call, Raise, Check
    public void fold() {
        this.hand = null;
        playerChips = 0;
        this.playing = false;
    }

    public void setCall(int calledAmt) {
        this.betAmt = calledAmt;
        this.playerChips -= calledAmt;
    }

    public void setRaise(int raisedAmt) {
        this.betAmt = raisedAmt;
        this.playerChips -= raisedAmt;
    }

    public Boolean setCheck(String answer) {
        if (answer.equals("Check")){
            return true;
        } else {
            return false;
        }
    }
}
